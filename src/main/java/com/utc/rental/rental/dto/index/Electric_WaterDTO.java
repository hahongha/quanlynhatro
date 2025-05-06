package com.utc.rental.rental.dto.index;

import java.time.LocalDate;
import java.util.List;

import com.utc.rental.rental.dto.room.RoomDTO;
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
public class Electric_WaterDTO {
	private String id;

	private String type;

	private int month;

	private int year;

	private double previousIndex;

	private double currentIndex;
	
	private LocalDate recordDate;

	private double value;

	private Long pricePerUnit;

	private Long totalAmount;

	private RoomDTO room;
}
