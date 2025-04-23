package com.utc.rental.rental.dto;

import java.time.LocalDate;

import com.utc.rental.rental.dto.user.UserDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RenterDTO {
	
	String id;
	
	String fullName;

	// gioi tinh
	String gender;

	// trang thai
	String status;

	// so dien thoai
	String phone;

	// ngay sinh
	LocalDate dob;

	// cccd
	@NotNull
	String identification;

	// quê quán
	@NotNull
	String placeOfOrigin;

	// nơi thường trú
	@NotNull
	String address;

	String familyPhone;

	// dang ki tam tru chua
	Boolean isRegister;
	
	UserDTO user;
}
