package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.payment.PaymentDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchPayment;


public interface PaymentService {
	PaymentDTO create(PaymentDTO PaymentDTO);

	Boolean delete(String id);
	
	PaymentDTO update(PaymentDTO PaymentDTO);
	
	PaymentDTO get(String id);
	
	List<PaymentDTO> getAll();
	
	ResponseDTO<List<PaymentDTO>> search(SearchPayment searchPayment);
	
	Long count();
}
