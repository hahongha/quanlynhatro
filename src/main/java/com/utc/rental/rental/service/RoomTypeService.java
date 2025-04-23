//package com.utc.rental.rental.service;
//
//import java.util.List;
//
//import com.utc.rental.rental.dto.response.ResponseDTO;
//import com.utc.rental.rental.dto.roomType.RoomTypeDTO;
//import com.utc.rental.rental.dto.roomType.SearchRoomTypeDTO;
//import com.utc.rental.rental.entity.RoomType;
//
//public interface RoomTypeService {
//	RoomTypeDTO create(RoomTypeDTO RoomTypeDTO);
//
//	Boolean delete(Long id);
//	
//	RoomTypeDTO update(RoomTypeDTO RoomTypeDTO);
//	
//	RoomType get(Long id);
//	
//	List<RoomTypeDTO> getAll();
//	
//	ResponseDTO<List<RoomTypeDTO>> search(SearchRoomTypeDTO searchRoomTypeDTO);
//	
//	RoomType findByName(String RoomTypeName);
//	
//	Long count();
//}
