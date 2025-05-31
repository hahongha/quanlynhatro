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
import com.utc.rental.rental.dto.payment.PaymentDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchPayment;
import com.utc.rental.rental.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payment")
public class PaymentAPI {
	@Autowired
	private PaymentService paymentService;

	private static final String ENTITY_NAME = "Payment";

	@PostMapping("")
	public ResponseDTO<PaymentDTO> create(@RequestBody @Valid PaymentDTO PaymentDTO) throws URISyntaxException {
		paymentService.create(PaymentDTO);
		return ResponseDTO.<PaymentDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(PaymentDTO).build();
	}
	
	@PostMapping("/search")
	public ResponseDTO<List<PaymentDTO>> search(@RequestBody @Valid SearchPayment searchPayment) throws URISyntaxException {
		return paymentService.search(searchPayment);
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		paymentService.delete(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("")
	public ResponseDTO<PaymentDTO> update(@RequestBody PaymentDTO PaymentDTO) throws URISyntaxException {
		return ResponseDTO.<PaymentDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(paymentService.update(PaymentDTO)).build();
	}
	
	@GetMapping("/getAll")
    public ResponseDTO<List<PaymentDTO>> getAll() {
		return ResponseDTO.<List<PaymentDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(paymentService.getAll()).build();
    }
	
	@GetMapping("/{id}")
    public ResponseDTO<PaymentDTO> get(@PathVariable(value = "id") String id) {
		PaymentDTO PaymentDTO = paymentService.get(id);
		return ResponseDTO.<PaymentDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(PaymentDTO).build();
    }
}


