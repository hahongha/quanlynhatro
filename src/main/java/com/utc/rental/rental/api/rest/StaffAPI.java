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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchStaff;
import com.utc.rental.rental.dto.staff.StaffDTO;
import com.utc.rental.rental.service.CloudinaryService;
import com.utc.rental.rental.service.StaffService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/staff")
public class StaffAPI {
	@Autowired
	private StaffService StaffService;

	@Autowired
	private CloudinaryService cloudinaryService;
	

	private static final String ENTITY_NAME = "Staff";

	@PostMapping("")
	public ResponseDTO<StaffDTO> create(@RequestPart("Staff") StaffDTO StaffDTO, @RequestPart(value = "file", required = false)  MultipartFile file) throws URISyntaxException {
		if(file!=null) {
			String images = cloudinaryService.uploadImage(file, "Staff");
			StaffDTO.setImageUrl(images);
		}
		StaffService.create(StaffDTO);
		return ResponseDTO.<StaffDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(StaffDTO).build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		StaffService.delete(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("")
	public ResponseDTO<StaffDTO> update(@RequestPart("Staff") StaffDTO StaffDTO, @RequestPart(value = "file", required = false)  MultipartFile file) throws URISyntaxException {
		if(file!=null) {
			String images = cloudinaryService.uploadImage(file, "Staff");
			StaffDTO.setImageUrl(images);
			
		}
		StaffService.update(StaffDTO);
		return ResponseDTO.<StaffDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(StaffDTO).build();
	}
	
	@GetMapping("/getAll")
    public ResponseDTO<List<StaffDTO>> getAll() {
		return ResponseDTO.<List<StaffDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(StaffService.getAll()).build();
    }
	
	@PostMapping("/search")
    public ResponseDTO<List<StaffDTO>> search(@RequestBody @Valid SearchStaff searchDTO) {
        return StaffService.search(searchDTO);
    }
}


