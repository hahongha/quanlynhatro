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
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.room.RoomDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.Room;
import com.utc.rental.rental.service.CloudinaryService;
import com.utc.rental.rental.service.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/room")
public class RoomAPI {
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CloudinaryService cloudinaryService;

	private static final String ENTITY_NAME = "room";

	@PostMapping("/search")
	public ResponseDTO<List<RoomDTO>> search(@RequestBody @Valid SearchDTO searchRoomDTO) {
		return roomService.search(searchRoomDTO);
	}

	@PostMapping("")
	public ResponseDTO<RoomDTO> create(@RequestPart("room") RoomDTO roomDTO, @RequestPart(value = "file", required = false)  MultipartFile[] files) throws URISyntaxException {
		if (files!= null) {
			List<String> images = cloudinaryService.uploadMultipartFile(files, "ROOM_TYPES/"+ roomDTO.getRoomNumber());
			if(images!= null && images.size()>0)
				roomDTO.getImages().addAll(images);
		}
		roomService.create(roomDTO);
		return ResponseDTO.<RoomDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(roomDTO).build();
	}

	@PostMapping("/createList")
	public ResponseDTO<List<RoomDTO>> createList(@RequestBody @Valid List<RoomDTO> rs)
			throws URISyntaxException {
		for (RoomDTO roomDTO2 : rs) {
			roomService.create(roomDTO2);
		}
		return ResponseDTO.<List<RoomDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(rs).build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		roomService.delete(Long.valueOf(id));
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}

	@PutMapping("")
	public ResponseDTO<RoomDTO> update(@RequestPart("room") RoomDTO roomDTO, @RequestPart(value = "file", required = false)  MultipartFile[] files) throws URISyntaxException {

		if (roomDTO.getId() == null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_room");
		}
		
		if (files!= null) {
			List<String> images = cloudinaryService.uploadMultipartFile(files, "ROOM_TYPES/"+ roomDTO.getRoomNumber());
			if(images!= null && images.size()>0)
				roomDTO.getImages().addAll(images);
		}
		RoomDTO roomDTO2 = roomService.update(roomDTO);
		return ResponseDTO.<RoomDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(roomService.update(roomDTO)).build();
	}

	@GetMapping("/getAll")
	public ResponseDTO<List<RoomDTO>> getAll() {
		return ResponseDTO.<List<RoomDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(roomService.getAll()).build();
	}
	
	@GetMapping("/{id}")
    public ResponseDTO<RoomDTO> get(@PathVariable(value = "id") String id) {
		Room Room =roomService.get(Long.valueOf(id));
		ModelMapper mapper = new ModelMapper();
		return ResponseDTO.<RoomDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(mapper.map(Room, RoomDTO.class)).build();
    }
}
