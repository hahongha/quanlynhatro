package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.room.RoomDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.Room;

public interface RoomService {
	RoomDTO create(RoomDTO RoomDTO);

	Boolean delete(Long id);
	
	RoomDTO update(RoomDTO RoomDTO);
	
	Room get(Long id);
	
	List<RoomDTO> getAll();
	
	ResponseDTO<List<RoomDTO>> search(SearchDTO searchRoomDTO);
	
	Room findByName(String RoomName);
	
	Long count();
	
}
