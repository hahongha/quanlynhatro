package com.utc.rental.rental.api.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.SearchDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.user.UpdatePasswordDTO;
import com.utc.rental.rental.dto.user.UserDTO;
import com.utc.rental.rental.dto.user.UserUpdateDTO;
import com.utc.rental.rental.entity.User;
import com.utc.rental.rental.security.securityv2.CurrentUser;
import com.utc.rental.rental.security.securityv2.UserPrincipal;
import com.utc.rental.rental.service.CloudinaryService;
import com.utc.rental.rental.service.UserService;

import io.jsonwebtoken.io.IOException;
import jakarta.validation.Valid;

import java.net.URISyntaxException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

	@Autowired
	private UserService userService;

	private static final String ENTITY_NAME = "USER";
	
	@Autowired
	private CloudinaryService cloudinaryService;

	@PostMapping("")
	public ResponseDTO<UserDTO> create(@RequestBody UserDTO userDTO) throws URISyntaxException {
		if (userDTO.getUserName() == null) {
			throw new BadRequestAlertException("missing data", ENTITY_NAME, "missingData");
		}
		userService.create(userDTO);
		return ResponseDTO.<UserDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(userDTO).build();

	}

	@GetMapping("/{id}")
	public ResponseDTO<UserDTO> get(@PathVariable(value = "id") String id) {
		User user = userService.get(id);
		UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);
		return ResponseDTO.<UserDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(userDTO)
				.build();
	}
	
	@GetMapping("/information")
	public ResponseDTO<UserDTO> getInfor(@CurrentUser UserPrincipal userPrincipal) {
		User user = userService.get(userPrincipal.getUser_id());
		UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);
		return ResponseDTO.<UserDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(userDTO)
				.build();
	}

	@PutMapping("/update-password")
	public ResponseDTO<Void> updatePassword(@RequestBody @Valid UpdatePasswordDTO updatePassword) throws IOException {
		userService.updatePassword(updatePassword);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("")
	public ResponseDTO<Void> update(@RequestBody @Valid UserDTO userDTO) throws IOException {
		userService.update(userDTO);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}

	@PostMapping("/search")
	public ResponseDTO<List<UserDTO>> search(@RequestBody @Valid SearchDTO searchDTO) {
		return userService.search(searchDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id) throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing id", ENTITY_NAME, "missing_id");
		}
		userService.delete(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	//upload ảnh riêng
	@PostMapping("/avatarUpload")
    public ResponseEntity<String> uploadImage(@CurrentUser UserPrincipal currentUser, @RequestParam("file") MultipartFile file) {
		UserDTO user = userService.getDTO(currentUser.getUser_id());
        try {
            String publicId = "users/user_" + user.getUserId(); // Định danh ảnh theo userId để ghi đè
            String imageUrl = cloudinaryService.uploadImage(file, publicId);
            user.setImageUrl(imageUrl);
            userService.update(user);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.internalServerError().body("Lỗi khi upload ảnh: " + e.getMessage());
        }
    }
	
	@PutMapping("/update-profile")
    public ResponseDTO<UserDTO> updateProfile(@RequestBody UserUpdateDTO updatedUser, @CurrentUser UserPrincipal userPrincipal) {
            UserDTO user = userService.updateUserProfile(updatedUser);
            return ResponseDTO.<UserDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(user).build();
    }
}

