package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.contractMember.ContractMemberDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.ContractMember;

public interface ContractMemberService {
	ContractMemberDTO create(ContractMemberDTO ContractMemberDTO);

	Boolean delete(Long id);

	ContractMemberDTO update(ContractMemberDTO ContractMemberDTO);

	ContractMember get(Long id);

	List<ContractMemberDTO> getAll();

	// lấy thông tin của các contractMember của phòng đó
	List<ContractMemberDTO> getContractMemberFromContract(String id);
	
//	List<ContractMemberDTO> getContractMemberFromContract(String id, String status);

	ResponseDTO<List<ContractMemberDTO>> search(SearchDTO searchDTO);

	Long count();
}
