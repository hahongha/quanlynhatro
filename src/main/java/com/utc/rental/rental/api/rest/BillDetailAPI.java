package com.utc.rental.rental.api.rest;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utc.rental.rental.dto.bill.BillDetailDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.service.BillDetailService;

@RestController
@RequestMapping("/api/billDetail")
public class BillDetailAPI {
	
	@Autowired
	private BillDetailService billDetailService;
	
	@GetMapping("/getByBillId")
	public ResponseDTO<List<BillDetailDTO>> create(@RequestParam("billId") String billId) throws URISyntaxException {
		return ResponseDTO.<List<BillDetailDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(billDetailService.getByBillId(billId)).build();
	}
}
