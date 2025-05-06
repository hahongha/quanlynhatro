package com.utc.rental.rental.entity;

import java.time.LocalDate;

import com.utc.rental.rental.config.Constants;
import com.utc.rental.rental.dto.contract.ContractResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "contract_member")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"contract"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractMember extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
	private Long id;

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
	
	String imageUrl;

	// ràng buộc với hợp đồng nào
	@ManyToOne(fetch = FetchType.LAZY)
	Contractt contract;

}
