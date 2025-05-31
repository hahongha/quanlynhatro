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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.index.Electric_WaterDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchEW;
import com.utc.rental.rental.entity.Electric_Water;
import com.utc.rental.rental.service.CloudinaryService;
import com.utc.rental.rental.service.Electric_WaterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ew")
public class ElectricWaterAPI {
	@Autowired
	private Electric_WaterService electric_WaterService;

	private static final String ENTITY_NAME = "Electric_Water";

	@PostMapping("/search")
	public ResponseDTO<List<Electric_WaterDTO>> search(@RequestBody @Valid SearchEW searchEW) {
		return electric_WaterService.search(searchEW);
	}

	@PostMapping("")
	public ResponseDTO<Electric_WaterDTO> create(@RequestBody Electric_WaterDTO Electric_WaterDTO) throws URISyntaxException {
		electric_WaterService.create(Electric_WaterDTO);
		return ResponseDTO.<Electric_WaterDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(Electric_WaterDTO).build();
	}
	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		electric_WaterService.delete(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}

	@PutMapping("")
	public ResponseDTO<Electric_WaterDTO> update(@RequestBody Electric_WaterDTO Electric_WaterDTO) throws URISyntaxException {

		if (Electric_WaterDTO.getId() == null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_Electric_Water");
		}
		return ResponseDTO.<Electric_WaterDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(electric_WaterService.update(Electric_WaterDTO)).build();
	}

	@GetMapping("/getAll")
	public ResponseDTO<List<Electric_WaterDTO>> getAll() {
		return ResponseDTO.<List<Electric_WaterDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(electric_WaterService.getAll()).build();
	}
	
	@GetMapping("/{id}")
    public ResponseDTO<Electric_WaterDTO> get(@PathVariable(value = "id") String id) {
		return ResponseDTO.<Electric_WaterDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(electric_WaterService.get(id)).build();
    }
}

