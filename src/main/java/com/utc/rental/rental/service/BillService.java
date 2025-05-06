package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.bill.BillDTO;
import com.utc.rental.rental.dto.renter.RenterDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchBill;
import com.utc.rental.rental.dto.search.SearchRenter;


public interface BillService {
	BillDTO create(BillDTO BillDTO);

	Boolean delete(String id);
	
	BillDTO update(BillDTO BillDTO);
	
	BillDTO get(String id);
	
	List<BillDTO> getAll();
	
	ResponseDTO<List<BillDTO>> search(SearchBill searchDTO);
	
	Long count();
}
