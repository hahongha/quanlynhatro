package com.utc.rental.rental.dto.room;

import java.util.ArrayList;
import java.util.List;

import com.utc.rental.rental.config.StringListConverter;
import com.utc.rental.rental.dto.contract.ContractDTO;
import com.utc.rental.rental.dto.roomType.RoomTypeDTO;
import com.utc.rental.rental.entity.RoomType;

import jakarta.persistence.Convert;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	private Long id;

	String roomNumber;

	//trạng thái hiện tại( hoạt động, đang sửa, ...)
	String status;
	
	Long cost;
	
	//có đang thuê không
	Boolean isActive;
	
	List<String> funiture;
	
	// mô tả về phòng trọ
	String description;
	
	//số người thuê hiện tại
	int number;
	
	//electric
	double electricIndex;
	//water
	double waterIndex;
		
	RoomTypeDTO room_Type;
	
	//các hình ảnh thực tế
	List<String> images;
	
	String contractId;
}
