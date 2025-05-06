package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.contract.ContractDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.Contractt;


public interface ContractService {
	ContractDTO create(ContractDTO ContractDTO);

	Boolean delete(String id);
	
	ContractDTO update(ContractDTO ContractDTO);
	
	Contractt get(String id);
	
	List<ContractDTO> getAll();
	
	Long count();
	
	ResponseDTO<List<ContractDTO>> search(SearchDTO searchDTO);
	
	ContractDTO renewContract(ContractDTO contractDTO);
	
	ContractDTO paidDeposit(ContractDTO contractDTO);
	
	ContractDTO cancelContract(ContractDTO contractDTO);
}
