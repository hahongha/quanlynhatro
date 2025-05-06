package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.roomReturn.RoomReturnDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchRoomReturn;
import com.utc.rental.rental.entity.Room_Service;

public interface RoomReturnHistoryService {
	RoomReturnDTO create(RoomReturnDTO RoomReturnDTO);

	Boolean delete(String id);
	
	RoomReturnDTO update(RoomReturnDTO RoomReturnDTO);
	
	RoomReturnDTO get(String id);
	
	List<RoomReturnDTO> getAll();
	
	ResponseDTO<List<RoomReturnDTO>> search(SearchRoomReturn searchDTO);
	
	Long count();
}
