package com.utc.rental.rental.api.rest;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.cloudinary.provisioning.Account.Role;
import com.utc.rental.rental.api.error.UnauthorizedException;
import com.utc.rental.rental.config.DefaultValue.StatusActRef;
import com.utc.rental.rental.dto.authority.AuthorityDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.user.UserDTO;
import com.utc.rental.rental.dto.user.UserLogin;
import com.utc.rental.rental.dto.user.UserResponse;
import com.utc.rental.rental.entity.User;
import com.utc.rental.rental.security.securityv2.CurrentUser;
import com.utc.rental.rental.security.securityv2.UserPrincipal;
import com.utc.rental.rental.service.AuthService;
import com.utc.rental.rental.service.AuthorityService;
import com.utc.rental.rental.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthAPI {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthorityService authorityService;

	private static final Logger LOG = LoggerFactory.getLogger(AuthAPI.class);
	
	@PostMapping("/signin")
	public ResponseDTO<String> signin(@Valid @RequestBody UserLogin loginRequest) {
		try {
			
			if(loginRequest.getUserName()==null || loginRequest.getPassword()==null) {
				LOG.error("Invalid username or password:" +loginRequest.toString());
				throw new UnauthorizedException("Invalid username or password");
			}
			
			User user = userService.findByUserName(loginRequest.getUserName());
			if(!user.getStatus().equals(StatusActRef.ACTIVE.toString())) {
				LOG.error("User " + loginRequest.toString() +" not active");
				throw new UnauthorizedException("Account is inactive");
			}
			
			Boolean compare_password = BCrypt.checkpw(loginRequest.getPassword(), user.getPassword());

			if (!compare_password) {
				LOG.error("Password wrong:"+ loginRequest.toString());
				throw new UnauthorizedException("User not found");
			}
			
			return authService.signin(loginRequest, user);

		} catch (Exception e) {
			throw Problem.builder().withStatus(Status.INTERNAL_SERVER_ERROR).withDetail("SERVER ERROR").build();
		}
	}
	
	@PostMapping("/logout")
	public ResponseDTO<Void> logout() {
		try {
			

		} catch (Exception e) {
			throw Problem.builder().withStatus(Status.INTERNAL_SERVER_ERROR).withDetail("SERVER ERROR").build();
		}
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}

	@PostMapping("/refreshToken")
	public ResponseDTO<String> handleRefreshToken(
			@RequestParam(value = "refreshtoken", required = true) String refreshtoken) {
		try {
			return authService.handleRefreshToken(refreshtoken);

		} catch (Exception e) {
			throw Problem.builder().withStatus(Status.INTERNAL_SERVER_ERROR).withDetail("SERVER ERROR").build();
		}
	}
	
	@GetMapping("/userProfile")
	public ResponseDTO<UserResponse> getProfile(@CurrentUser UserPrincipal user) {
		try {
			User u = userService.findByUserName(user.getUsername());
			UserResponse userDTO = new ModelMapper().map(u, UserResponse.class);
			return ResponseDTO.<UserResponse>builder().code(String.valueOf(HttpStatus.OK.value())).data(userDTO).build();

		} catch (Exception e) {
			e.printStackTrace();
			throw Problem.builder().withStatus(Status.INTERNAL_SERVER_ERROR).withDetail("SERVER ERROR").build();
		}
	}
	
	@GetMapping("/getRole")
	public ResponseDTO<List<AuthorityDTO>> getRole(@CurrentUser UserPrincipal user) {
		try {
			User u = userService.findByUserName(user.getUsername());
			List<AuthorityDTO> authorityDTOs = authorityService.getByRoleId(u.getRole().getId());
			return ResponseDTO.<List<AuthorityDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(authorityDTOs).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw Problem.builder().withStatus(Status.INTERNAL_SERVER_ERROR).withDetail("SERVER ERROR").build();
		}
	}
}

