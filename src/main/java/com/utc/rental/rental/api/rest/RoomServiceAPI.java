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
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.room_service.RoomServiceDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchServiceRoom;
import com.utc.rental.rental.service.ServiceRoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roomService")
public class RoomServiceAPI {
	@Autowired
	private ServiceRoomService serviceRoomService;

	private static final String ENTITY_NAME = "RoomService";

	@PostMapping("")
	public ResponseDTO<RoomServiceDTO> create(@RequestBody RoomServiceDTO RoomServiceDTO) throws URISyntaxException {
		serviceRoomService.create(RoomServiceDTO);
		return ResponseDTO.<RoomServiceDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(RoomServiceDTO)
				.build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id) throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data", ENTITY_NAME, "missing_id");
		}
		serviceRoomService.delete(Long.valueOf(id));

		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}

	@PutMapping("")
	public ResponseDTO<RoomServiceDTO> update(@RequestBody RoomServiceDTO RoomServiceDTO) throws URISyntaxException {
		return ResponseDTO.<RoomServiceDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(serviceRoomService.update(RoomServiceDTO)).build();
	}

	@GetMapping("/getAll")
	public ResponseDTO<List<RoomServiceDTO>> getAll() {
		return ResponseDTO.<List<RoomServiceDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(serviceRoomService.getAll()).build();
	}

	@PostMapping("/search")
	public ResponseDTO<List<RoomServiceDTO>> search(@RequestBody @Valid SearchServiceRoom searchDTO) {
		return serviceRoomService.search(searchDTO);
	}

	@GetMapping("/{id}")
	public ResponseDTO<RoomServiceDTO> get(@PathVariable(value = "id") String id) {

		return ResponseDTO.<RoomServiceDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(serviceRoomService.get(Long.valueOf(id))).build();
	}
}
