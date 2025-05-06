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

import com.utc.rental.rental.dto.contract.ContractDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.Contractt;
import com.utc.rental.rental.service.ContractService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contract")
public class ContractAPI {
	@Autowired
	private ContractService ContractService;

	private static final String ENTITY_NAME = "Contract";

	@PostMapping("/search")
	public ResponseDTO<List<ContractDTO>> search(@RequestBody @Valid SearchDTO searchContractDTO) {
		return ContractService.search(searchContractDTO);
	}

	@PostMapping("")
	public ResponseDTO<ContractDTO> create(@RequestBody @Valid ContractDTO ContractDTO) throws URISyntaxException {
		ContractService.create(ContractDTO);
		return ResponseDTO.<ContractDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(ContractDTO).build();
	}

	@PostMapping("/createList")
	public ResponseDTO<List<ContractDTO>> createList(@RequestBody @Valid List<ContractDTO> rs)
			throws URISyntaxException {
		for (ContractDTO ContractDTO2 : rs) {
			ContractService.create(ContractDTO2);
		}
		return ResponseDTO.<List<ContractDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(rs).build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		ContractService.delete(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}

	@PutMapping("")
	public ResponseDTO<ContractDTO> update(@RequestBody ContractDTO ContractDTO) throws URISyntaxException {

		if (ContractDTO.getId() == null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_Contract");
		}
		return ResponseDTO.<ContractDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(ContractService.update(ContractDTO)).build();
	}

	@GetMapping("/getAll")
	public ResponseDTO<List<ContractDTO>> getAll() {
		return ResponseDTO.<List<ContractDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(ContractService.getAll()).build();
	}
	
	@GetMapping("/{id}")
    public ResponseDTO<ContractDTO> get(@PathVariable(value = "id") String id) {
		Contractt Contract =ContractService.get(id);
		ModelMapper mapper = new ModelMapper();
		return ResponseDTO.<ContractDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(mapper.map(Contract, ContractDTO.class)).build();
    }
}

