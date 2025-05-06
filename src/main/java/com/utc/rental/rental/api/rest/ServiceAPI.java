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
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.service.ServiceDTO;
import com.utc.rental.rental.service.Room_ServiceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/service")
public class ServiceAPI {
	@Autowired
	private Room_ServiceService roomServiceService;

	private static final String ENTITY_NAME = "service";

	@PostMapping("")
	public ResponseDTO<ServiceDTO> create(@RequestBody @Valid ServiceDTO ServiceDTO) throws URISyntaxException {
		if (ServiceDTO.getServiceName() == null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_Service");
		}
		roomServiceService.create(ServiceDTO);
		return ResponseDTO.<ServiceDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(ServiceDTO).build();
	}
	
	@PostMapping("/createList")
	public ResponseDTO<List<ServiceDTO>> createList(@RequestBody @Valid List<ServiceDTO> ServiceDTO) throws URISyntaxException {
		for (ServiceDTO serviceDTO2 : ServiceDTO) {
			roomServiceService.create(serviceDTO2);
		}
		
		return ResponseDTO.<List<ServiceDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(ServiceDTO).build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		roomServiceService.delete(Long.valueOf(id));
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("")
	public ResponseDTO<ServiceDTO> update(@RequestBody ServiceDTO ServiceDTO) throws URISyntaxException {

		if (ServiceDTO.getId() == null || ServiceDTO.getServiceName()==null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_Service");
		}
		return ResponseDTO.<ServiceDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(roomServiceService.update(ServiceDTO)).build();
	}
	
	@GetMapping("/getAll")
    public ResponseDTO<List<ServiceDTO>> getAll() {
		return ResponseDTO.<List<ServiceDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(roomServiceService.getAll()).build();
    }
	
	@PostMapping("/search")
    public ResponseDTO<List<ServiceDTO>> search(@RequestBody @Valid SearchDTO searchDTO) {
        return roomServiceService.search(searchDTO);
    }
	
	@GetMapping("/{id}")
    public ResponseDTO<ServiceDTO> get(@PathVariable(value = "id") String id) {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		return ResponseDTO.<ServiceDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(roomServiceService.getDTO(Long.valueOf(id))).build();
    }
}

