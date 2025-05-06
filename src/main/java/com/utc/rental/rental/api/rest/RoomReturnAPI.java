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
import com.utc.rental.rental.dto.roomReturn.RoomReturnDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchRoomReturn;
import com.utc.rental.rental.service.RoomReturnHistoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roomReturn")
public class RoomReturnAPI {
	@Autowired
	private RoomReturnHistoryService roomReturnHistoryService;

	private static final String ENTITY_NAME = "RoomReturn";

	@PostMapping("")
	public ResponseDTO<RoomReturnDTO> create(@RequestBody @Valid RoomReturnDTO RoomReturnDTO) throws URISyntaxException {
		roomReturnHistoryService.create(RoomReturnDTO);
		return ResponseDTO.<RoomReturnDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(RoomReturnDTO).build();
	}
	
	@PostMapping("/createList")
	public ResponseDTO<List<RoomReturnDTO>> createList(@RequestBody @Valid List<RoomReturnDTO> RoomReturnDTO) throws URISyntaxException {
		for (RoomReturnDTO RoomReturnDTO2 : RoomReturnDTO) {
			roomReturnHistoryService.create(RoomReturnDTO2);
		}
		
		return ResponseDTO.<List<RoomReturnDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(RoomReturnDTO).build();
	}

	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		roomReturnHistoryService.delete(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("")
	public ResponseDTO<RoomReturnDTO> update(@RequestBody RoomReturnDTO RoomReturnDTO) throws URISyntaxException {

		return ResponseDTO.<RoomReturnDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(roomReturnHistoryService.update(RoomReturnDTO)).build();
	}
	
	@GetMapping("/getAll")
    public ResponseDTO<List<RoomReturnDTO>> getAll() {
		return ResponseDTO.<List<RoomReturnDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(roomReturnHistoryService.getAll()).build();
    }
	
	@PostMapping("/search")
    public ResponseDTO<List<RoomReturnDTO>> search(@RequestBody @Valid SearchRoomReturn searchDTO) {
        return roomReturnHistoryService.search(searchDTO);
    }
	
	@GetMapping("/{id}")
    public ResponseDTO<RoomReturnDTO> get(@PathVariable(value = "id") String id) {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing data",
					ENTITY_NAME, "missing_id");
		}
		return ResponseDTO.<RoomReturnDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(roomReturnHistoryService.get(id)).build();
    }
}

