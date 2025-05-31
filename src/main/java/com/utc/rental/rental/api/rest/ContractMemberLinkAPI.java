package com.utc.rental.rental.api.rest;

import com.utc.rental.rental.dto.cmlink.CMLinkDTO;
import com.utc.rental.rental.dto.contractMember.ContractMemberDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.entity.ContractMemberLink;
import com.utc.rental.rental.service.ContractMemberLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contract_member_links")
public class ContractMemberLinkAPI {

    @Autowired
    private ContractMemberLinkService service;

    @PostMapping("")
    public ResponseDTO<CMLinkDTO> create(@RequestBody CMLinkDTO link) {
    	return ResponseDTO.<CMLinkDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(service.save(link)).build();
    }

    @PutMapping("")
    public ResponseDTO<CMLinkDTO> update(@RequestBody CMLinkDTO link) {
    	return ResponseDTO.<CMLinkDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(service.update(link)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        service.cancel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseDTO<CMLinkDTO> getById(@PathVariable Long id) {
    	return ResponseDTO.<CMLinkDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(service.findById(id)).build();
    }
    @GetMapping("/search")
    public ResponseDTO<List<CMLinkDTO>> findByContractOrMember(
            @RequestParam(required = false) String contractId,
            @RequestParam(required = false) Long contractMemberId
    ) {
        if (contractId != null && contractMemberId != null) {
        	return ResponseDTO.<List<CMLinkDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
    				.data(service.getByContractMember_Contract(contractMemberId, contractId)).build();
        } else if (contractId == null && contractMemberId!= null) {
        	return ResponseDTO.<List<CMLinkDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
    				.data(service.getByContractMember(contractMemberId)).build();
        } else if (contractMemberId == null && contractId!=null) {
        	return ResponseDTO.<List<CMLinkDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
    				.data(service.getByContract(contractId)).build();
        } else {
        	return ResponseDTO.<List<CMLinkDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
    				.data(service.findAll()).build();
        }
    }


    @GetMapping
    public ResponseDTO<List<CMLinkDTO>> getAll() {
    	return ResponseDTO.<List<CMLinkDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(service.findAll()).build();
    }
}
