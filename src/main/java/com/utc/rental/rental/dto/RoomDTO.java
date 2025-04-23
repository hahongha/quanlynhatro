package com.utc.rental.rental.dto;

import java.util.ArrayList;
import java.util.List;

import com.utc.rental.rental.dto.roomType.RoomTypeDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDTO {
	Long id;

	String roomNumber;

	//trạng thái hiện tại( hoạt động, đang sửa, ...)
	String status;
	
	//có đang thuê không
	Boolean isActive;
	
	// mô tả về phòng trọ
	String description;
	
	//số người thuê hiện tại
	int number;
	
	//electric
	double electricIndex;
	//water
	double waterIndex;
	
	String idRenter;
	
	String nameRender;
	
	RoomTypeDTO room_Type;
	
	List<String> images = new ArrayList<String>();
}
