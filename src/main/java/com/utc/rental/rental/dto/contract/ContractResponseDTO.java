package com.utc.rental.rental.dto.contract;

import com.utc.rental.rental.dto.RenterDTO;
import com.utc.rental.rental.dto.RoomDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractResponseDTO {
	String id;

	// nguoi thue
	RenterDTO renter;
	// phong thue
	RoomDTO room;
}
