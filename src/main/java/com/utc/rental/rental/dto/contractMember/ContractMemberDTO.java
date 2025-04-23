package com.utc.rental.rental.dto.contractMember;

import java.time.LocalDate;

import com.utc.rental.rental.dto.contract.ContractResponseDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractMemberDTO {

	Long id;
	
	LocalDate createAt;

	LocalDate updateAt;

	String createBy;

	// ho ten
	String fullName;

	// gioi tinh
	String gender;

	// trang thai
	String status;

	// so dien thoai
	String phone;

	// ngay sinh
	LocalDate dob;

	// quê quán
	String placeOfOrigin;

	// nơi thường trú
	String address;

	String familyPhone;

	// dang ki tam tru chua
	Boolean isRegister;

	// cccd
	String identification;
	
	String rentalRelationship;

	ContractResponseDTO contractResponseDTO;
	
	String imageUrl;

}
