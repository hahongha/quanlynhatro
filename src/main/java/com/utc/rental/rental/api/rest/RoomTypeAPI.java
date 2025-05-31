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

import com.cloudinary.Cloudinary;
import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.roomType.RoomTypeDTO;
import com.utc.rental.rental.dto.roomType.SearchRoomTypeDTO;
import com.utc.rental.rental.entity.RoomType;
import com.utc.rental.rental.service.CloudinaryService;
import com.utc.rental.rental.service.RoomTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roomType")
public class RoomTypeAPI {
	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private CloudinaryService cloudinaryService;

	private static final String ENTITY_NAME = "roomType";

	
	@PostMapping("/search")
    public ResponseDTO<List<RoomTypeDTO>> search(@RequestBody @Valid SearchRoomTypeDTO searchRoomTypeDTO) {
        return roomTypeService.search(searchRoomTypeDTO);
    }
	
	@PostMapping("")
	public ResponseDTO<RoomTypeDTO> create(@RequestPart("roomType") RoomTypeDTO roomTypeDTO, @RequestPart(value = "file", required = false) MultipartFile[] files) throws URISyntaxException {
		if (files!= null) {
			List<String> images = cloudinaryService.uploadMultipartFile(files, "ROOM_TYPES/"+ roomTypeDTO.getName());
			if(images!= null && images.size()>0)
				roomTypeDTO.getImageList().addAll(images);
		}
		roomTypeService.create(roomTypeDTO);
		return ResponseDTO.<RoomTypeDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(roomTypeDTO).build();
	}
	
	@PostMapping("/createList")
	public ResponseDTO<List<RoomTypeDTO>> createList(@RequestBody @Valid List<RoomTypeDTO> rs) throws URISyntaxException {
		for (RoomTypeDTO roomTypeDTO2 : rs) {
			roomTypeService.create(roomTypeDTO2);
		}
		return ResponseDTO.<List<RoomTypeDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(rs).build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		roomTypeService.delete(Long.valueOf(id));
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("")
	public ResponseDTO<RoomTypeDTO> update(@RequestPart("roomType") RoomTypeDTO roomTypeDTO, @RequestPart(value = "file", required = false)  MultipartFile[] files) throws URISyntaxException {

		if (roomTypeDTO.getId() == null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_roomType");
		}
		if (files!= null) {
			List<String> images = cloudinaryService.uploadMultipartFile(files, "ROOM_TYPES/"+ roomTypeDTO.getName());
			if(images!= null && images.size()>0)
				roomTypeDTO.getImageList().addAll(images);
		}
		return ResponseDTO.<RoomTypeDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(roomTypeService.update(roomTypeDTO)).build();
	}
	
	@GetMapping("/getAll")
    public ResponseDTO<List<RoomTypeDTO>> getAll() {
		return ResponseDTO.<List<RoomTypeDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(roomTypeService.getAll()).build();
    }
	@GetMapping("/{id}")
    public ResponseDTO<RoomTypeDTO> get(@PathVariable(value = "id") String id) {
		RoomType RoomType =roomTypeService.get(Long.valueOf(id));
		ModelMapper mapper = new ModelMapper();
		return ResponseDTO.<RoomTypeDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(mapper.map(RoomType, RoomTypeDTO.class)).build();
    }
}

