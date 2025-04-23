package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.bill.BillDTO;


public interface BillService {
	BillDTO create(BillDTO BillDTO);

	Boolean delete(String id);
	
	BillDTO update(BillDTO BillDTO);
	
	BillDTO get(String id);
	
	List<BillDTO> getAll();
	Long count();
}
