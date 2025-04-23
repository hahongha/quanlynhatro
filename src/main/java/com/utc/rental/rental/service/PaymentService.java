package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.PaymentDTO;


public interface PaymentService {
	PaymentDTO create(PaymentDTO PaymentDTO);

	Boolean delete(String id);
	
	PaymentDTO update(PaymentDTO PaymentDTO);
	
	PaymentDTO get(String id);
	
	List<PaymentDTO> getAll();
	Long count();
}
