package com.utc.rental.rental.dto.bill;

import java.time.LocalDate;
import java.util.List;

import com.utc.rental.rental.dto.billType.BillTypeDTO;
import com.utc.rental.rental.dto.renter.RenterDTO;
import com.utc.rental.rental.dto.room.RoomDTO;

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
public class RenterBill {
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
	
	BillTypeDTO billType;
}
