package com.utc.rental.rental.dto.service;

import java.time.LocalDate;

import com.utc.rental.rental.dto.renter.RenterDTO;
import com.utc.rental.rental.dto.user.UserDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceDTO {
	Long id;

	String serviceName;

	String status;

	Long value;
	
	Boolean isDefault;
	
	String createAt;
	
	String createBy;
	
	String updateAt;
	
}
