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
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.role.RoleDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.Role;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleAPI {
	@Autowired
	private com.utc.rental.rental.service.RoleService RoleService;

	private static final String ENTITY_NAME = "Role";

	@PostMapping("")
	public ResponseDTO<RoleDTO> create(@RequestBody RoleDTO RoleDTO) throws URISyntaxException {
		if (RoleDTO.getRoleName() == null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_Role");
		}
		RoleService.create(RoleDTO);
		return ResponseDTO.<RoleDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(RoleDTO).build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		RoleService.delete(Long.valueOf(id));
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("")
	public ResponseDTO<RoleDTO> update(@RequestBody RoleDTO RoleDTO) throws URISyntaxException {

		if (RoleDTO.getId() == null || RoleDTO.getRoleName()==null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_Role");
		}
		return ResponseDTO.<RoleDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(RoleService.update(RoleDTO)).build();
	}
	
	@GetMapping("/getAll")
    public ResponseDTO<List<RoleDTO>> getAll() {
		return ResponseDTO.<List<RoleDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(RoleService.getAll()).build();
    }
	
	@GetMapping("/{id}")
    public ResponseDTO<RoleDTO> get(@PathVariable(value = "id") String id) {
		Role role =RoleService.get(Long.valueOf(id));
		ModelMapper mapper = new ModelMapper();
		return ResponseDTO.<RoleDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(mapper.map(role, RoleDTO.class)).build();
    }
	
	@PostMapping("/search")
    public ResponseDTO<List<RoleDTO>> search(@RequestBody @Valid SearchDTO searchDTO) {
        return RoleService.search(searchDTO);
    }
}

