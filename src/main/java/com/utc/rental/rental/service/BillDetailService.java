package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.bill.BillDetailDTO;


public interface BillDetailService {
	BillDetailDTO create(BillDetailDTO BillDetailDTO);

	Boolean delete(String id);
	
	BillDetailDTO update(BillDetailDTO BillDetailDTO);
	
	BillDetailDTO get(String id);
	
	List<BillDetailDTO> getAll();
}
