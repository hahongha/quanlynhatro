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
import com.utc.rental.rental.dto.billType.BillTypeDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.service.BillTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/billType")
public class BillTypeAPI {
	@Autowired
	private BillTypeService BillTypeDTOService;

	private static final String ENTITY_NAME = "BillType";

	@PostMapping("")
	public ResponseDTO<BillTypeDTO> create(@RequestBody @Valid BillTypeDTO BillTypeDTO) throws URISyntaxException {
		if (BillTypeDTO.getName() == null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_BillTypeDTO");
		}
		BillTypeDTOService.create(BillTypeDTO);
		return ResponseDTO.<BillTypeDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(BillTypeDTO).build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		BillTypeDTOService.delete(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("")
	public ResponseDTO<BillTypeDTO> update(@RequestBody BillTypeDTO BillTypeDTO) throws URISyntaxException {

		if (BillTypeDTO.getId() == null || BillTypeDTO.getName()==null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_BillTypeDTO");
		}
		return ResponseDTO.<BillTypeDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(BillTypeDTOService.update(BillTypeDTO)).build();
	}
	
	@GetMapping("/getAll")
    public ResponseDTO<List<BillTypeDTO>> getAll() {
		return ResponseDTO.<List<BillTypeDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(BillTypeDTOService.getAll()).build();
    }
	
//	@GetMapping("/search")
//    public ResponseDTO<List<BillTypeDTO>> search(@RequestBody @Valid SearchDTO searchDTO) {
//        return BillTypeDTOService.search(searchDTO);
//    }
	
	@GetMapping("/{id}")
    public ResponseDTO<BillTypeDTO> get(@PathVariable(value = "id") String id) {
		BillTypeDTO BillTypeDTO =BillTypeDTOService.get(id);
		ModelMapper mapper = new ModelMapper();
		return ResponseDTO.<BillTypeDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(mapper.map(BillTypeDTO, BillTypeDTO.class)).build();
    }
}


