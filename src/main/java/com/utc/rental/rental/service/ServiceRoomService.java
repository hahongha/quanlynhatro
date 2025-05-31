package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.room_service.RoomServiceDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchServiceRoom;
import com.utc.rental.rental.entity.Room_Service;

public interface ServiceRoomService {
	RoomServiceDTO create(RoomServiceDTO RoomServiceDTO);

	Boolean delete(Long id);
	
	RoomServiceDTO update(RoomServiceDTO RoomServiceDTO);
	
	RoomServiceDTO getDTO(Long id);
	
	RoomServiceDTO get(Long id);
	
	List<RoomServiceDTO> getAll();
	
	ResponseDTO<List<RoomServiceDTO>> search(SearchServiceRoom searchRoomService);
	
	Long count();
}
