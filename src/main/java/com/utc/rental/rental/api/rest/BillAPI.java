package com.utc.rental.rental.api.rest;

import java.net.URISyntaxException;
import java.util.List;

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
import com.utc.rental.rental.dto.bill.BillDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchBill;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bill")
public class BillAPI {
	@Autowired
	private com.utc.rental.rental.service.BillService BillService;
	

	private static final String ENTITY_NAME = "Bill";

	@PostMapping("")
	public ResponseDTO<BillDTO> create(@RequestBody BillDTO BillDTO) throws URISyntaxException {
		BillService.create(BillDTO);
		return ResponseDTO.<BillDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(BillDTO).build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		BillService.delete(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("")
	public ResponseDTO<BillDTO> update(@RequestBody BillDTO BillDTO) throws URISyntaxException {
		return ResponseDTO.<BillDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(BillService.update(BillDTO)).build();
	}
	
	@GetMapping("/getAll")
    public ResponseDTO<List<BillDTO>> getAll() {
		return ResponseDTO.<List<BillDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(BillService.getAll()).build();
    }
	
	@PostMapping("/search")
    public ResponseDTO<List<BillDTO>> search(@RequestBody SearchBill searchDTO) {
        return BillService.search(searchDTO);
    }
	
	@GetMapping("/{id}")
    public ResponseDTO<BillDTO> get(@PathVariable(value = "id") String id) {
		
		return ResponseDTO.<BillDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(BillService.get(id)).build();
    }
}

