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
import com.utc.rental.rental.dto.SearchDTO;
import com.utc.rental.rental.dto.authority.AuthorityDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.entity.Authority;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authority")
public class AuthorityAPI {
	@Autowired
	private com.utc.rental.rental.service.AuthorityService authorityService;

	private static final String ENTITY_NAME = "Authority";

	@PostMapping("")
	public ResponseDTO<AuthorityDTO> create(@RequestBody @Valid AuthorityDTO AuthorityDTO) throws URISyntaxException {
		if (AuthorityDTO.getName() == null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_Authority");
		}
		authorityService.create(AuthorityDTO);
		return ResponseDTO.<AuthorityDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(AuthorityDTO).build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		authorityService.delete(Long.valueOf(id));
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("")
	public ResponseDTO<AuthorityDTO> update(@RequestBody AuthorityDTO authorityDTO) throws URISyntaxException {

		if (authorityDTO.getId() == null || authorityDTO.getName()==null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_Authority");
		}
		return ResponseDTO.<AuthorityDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(authorityService.update(authorityDTO)).build();
	}
	
	@GetMapping("/getAll")
    public ResponseDTO<List<AuthorityDTO>> getAll() {
		return ResponseDTO.<List<AuthorityDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(authorityService.getAll()).build();
    }
	
	@GetMapping("/search")
    public ResponseDTO<List<AuthorityDTO>> search(@RequestBody @Valid SearchDTO searchDTO) {
        return authorityService.search(searchDTO);
    }
	
	@GetMapping("/{id}")
    public ResponseDTO<AuthorityDTO> get(@PathVariable(value = "id") String id) {
		Authority Authority =authorityService.get(Long.valueOf(id));
		ModelMapper mapper = new ModelMapper();
		return ResponseDTO.<AuthorityDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(mapper.map(Authority, AuthorityDTO.class)).build();
    }
}

