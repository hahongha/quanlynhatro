package com.utc.rental.rental.dto.search;

import java.time.LocalDate;

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

	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private Long roomId;
}
