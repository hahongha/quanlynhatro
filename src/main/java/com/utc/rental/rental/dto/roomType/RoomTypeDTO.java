package com.utc.rental.rental.dto.roomType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypeDTO {
	Long id;

	String name;
	
	Long size;

	String furniture;
	
	String description;

	// giá tiền
	Long cost;
}
