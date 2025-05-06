package com.utc.rental.rental.dto.room_service;

import java.time.LocalDate;
import java.util.List;

import com.utc.rental.rental.dto.room.RoomDTO;
import com.utc.rental.rental.dto.roomType.RoomTypeDTO;
import com.utc.rental.rental.dto.service.ServiceDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomServiceDTO {

	Long id;

	ServiceDTO service;

	RoomDTO room;

	LocalDate startDate;

	LocalDate endDate;

	String status;
	
	int quantity;

	LocalDate createAt;

	LocalDate updateAt;

}
