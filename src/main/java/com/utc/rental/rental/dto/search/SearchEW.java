package com.utc.rental.rental.dto.search;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchEW {
	SearchDTO searchDTO;
	private String type;

	private int month;

	private int year;
	
	private Long roomId;
}
