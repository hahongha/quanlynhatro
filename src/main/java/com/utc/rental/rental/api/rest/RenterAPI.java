package com.utc.rental.rental.api.rest;

import java.net.URISyntaxException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.RenterDTO;
import com.utc.rental.rental.dto.SearchDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.user.UserDTO;
import com.utc.rental.rental.entity.Renter;
import com.utc.rental.rental.entity.User;
import com.utc.rental.rental.security.securityv2.CurrentUser;
import com.utc.rental.rental.security.securityv2.UserPrincipal;
import com.utc.rental.rental.service.RenterService;
import com.utc.rental.rental.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/renter")
public class RenterAPI {
	@Autowired
	private RenterService renterService;
	
	@Autowired
	private UserService userService;

	private static final String ENTITY_NAME = "Renter";

	@PostMapping("")
	public ResponseDTO<RenterDTO> create(@RequestBody RenterDTO RenterDTO) throws URISyntaxException {
		renterService.create(RenterDTO);
		return ResponseDTO.<RenterDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(RenterDTO).build();
	}
	
	@PostMapping("/createList")
	public ResponseDTO<List<RenterDTO>> createList(@RequestBody List<RenterDTO> RenterDTOs) throws URISyntaxException {
		for (RenterDTO renterDTO : RenterDTOs) {
			renterService.create(renterDTO);
		}
		return ResponseDTO.<List<RenterDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(RenterDTOs).build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		renterService.delete(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("")
	public ResponseDTO<RenterDTO> update(@RequestBody RenterDTO RenterDTO) throws URISyntaxException {
		return ResponseDTO.<RenterDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(renterService.update(RenterDTO)).build();
	}
	
	@GetMapping("/getAll")
    public ResponseDTO<List<RenterDTO>> getAll() {
		return ResponseDTO.<List<RenterDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(renterService.getAll()).build();
    }
	
	@PostMapping("/search")
    public ResponseDTO<List<RenterDTO>> search(@RequestBody @Valid SearchDTO searchDTO) {
        return renterService.search(searchDTO);
    }
	
	@GetMapping("/{id}")
    public ResponseDTO<RenterDTO> get(@PathVariable(value = "id") String id) {
		Renter Renter =renterService.get(id);
		ModelMapper mapper = new ModelMapper();
		return ResponseDTO.<RenterDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(mapper.map(Renter, RenterDTO.class)).build();
    }
	
	@GetMapping("/myProfile")
	public ResponseDTO<RenterDTO> myProfile(@CurrentUser UserPrincipal currentPrincipal) {
		if(currentPrincipal==null) return null;
		return ResponseDTO.<RenterDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(renterService.findByUserId(currentPrincipal.getUser_id())).build();
    }
}

