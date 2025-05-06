package com.utc.rental.rental.dto.renter;

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
public class UserRentalDTO {
	String id;

	String identification;

	Boolean isRegister;

	// so dien thoai
	String phone;

	// quê quán
	String placeOfOrigin;

	// nơi thường trú
	String address;

	String familyPhone;

	String imageUrl;

	String fullName;

	// gioi tinh
	String gender;

	// ngay sinh
	LocalDate dob;
	
	LocalDate createAt;
	
	LocalDate updateAt;
}
