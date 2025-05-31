package com.utc.rental.rental.api.rest;

import java.net.URISyntaxException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.contract.ContractResponseDTO;
import com.utc.rental.rental.dto.contractMember.ContractMemberDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.ContractMember;
import com.utc.rental.rental.service.CloudinaryService;
import com.utc.rental.rental.service.ContractMemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contractMember")
public class ContractMemberAPI {
	@Autowired
	private ContractMemberService ContractMemberService;
	
	@Autowired
	private CloudinaryService cloudinaryService;

	private static final String ENTITY_NAME = "ContractMember";

	@PostMapping("/search")
	public ResponseDTO<List<ContractMemberDTO>> search(@RequestBody @Valid SearchDTO searchContractMemberDTO) {
		return ContractMemberService.search(searchContractMemberDTO);
	}

	@PostMapping("")
	public ResponseDTO<ContractMemberDTO> create(@RequestPart("member") ContractMemberDTO contractMemberDTO, @RequestPart(name = "file", required = false) MultipartFile file)
			throws URISyntaxException {
		if(file!=null) {
			String images = cloudinaryService.uploadImage(file, "MEMBER");
			contractMemberDTO.setImageUrl(images);
		}
		ContractMemberService.create(contractMemberDTO);
		return ResponseDTO.<ContractMemberDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(contractMemberDTO).build();
	}

	@PostMapping("/createList")
	public ResponseDTO<List<ContractMemberDTO>> createList(@RequestBody @Valid List<ContractMemberDTO> rs)
			throws URISyntaxException {
		for (ContractMemberDTO ContractMemberDTO2 : rs) {
			ContractMemberService.create(ContractMemberDTO2);
		}
		return ResponseDTO.<List<ContractMemberDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(rs)
				.build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id) throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_id");
		}
		ContractMemberService.delete(Long.valueOf(id));
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}

	@PutMapping("")
	public ResponseDTO<ContractMemberDTO> update(@RequestPart("member") ContractMemberDTO contractMemberDTO, @RequestPart(name = "file", required = false) MultipartFile file)
			throws URISyntaxException {

		if (contractMemberDTO.getId() == null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_ContractMember");
		}
		if(file!=null) {
			String images = cloudinaryService.uploadImage(file, "MEMBER");
			contractMemberDTO.setImageUrl(images);
		}
		return ResponseDTO.<ContractMemberDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(ContractMemberService.update(contractMemberDTO)).build();
	}

	@GetMapping("/getAll")
	public ResponseDTO<List<ContractMemberDTO>> getAll() {
		return ResponseDTO.<List<ContractMemberDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(ContractMemberService.getAll()).build();
	}

	@GetMapping("/{id}")
	public ResponseDTO<ContractMemberDTO> get(@PathVariable(value = "id") String id) {
		ContractMember contractMember = ContractMemberService.get(Long.valueOf(id));
		ModelMapper mapper = new ModelMapper();
		ContractMemberDTO contractMemberDTO = mapper.map(contractMember, ContractMemberDTO.class);
		return ResponseDTO.<ContractMemberDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(contractMemberDTO).build();
	}
	
	@GetMapping("/getByIdentification/{id}")
	public ResponseDTO<ContractMemberDTO> getIdentification(@PathVariable(value = "id") String id) {
		ContractMember contractMember = ContractMemberService.getByCCCD(id);
		ModelMapper mapper = new ModelMapper();
		ContractMemberDTO contractMemberDTO = mapper.map(contractMember, ContractMemberDTO.class);
		return ResponseDTO.<ContractMemberDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(contractMemberDTO).build();
	}
	
	@GetMapping("/getByContract")
	public ResponseDTO<List<ContractMemberDTO>> getByContract(@RequestParam("contract") String contract) {
		return ResponseDTO.<List<ContractMemberDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(ContractMemberService.getContractMemberFromContract(contract)).build();
	}
}
