//package com.utc.rental.rental.entity;
//
//import com.utc.rental.rental.config.Constants;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.Pattern;
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.experimental.FieldDefaults;
//
//@Entity
//@Table(name = "contract_member")
//@Data
//@EqualsAndHashCode(callSuper = false)
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class ContractMember extends Person {
//	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
//	private Long id;
//
//	// cccd
//	String identification;
//	
//	String imageUrl;
//
//	// mối quan hệ với người thuê là gì
//	String rentalRelationship;
//	
//	@Pattern(regexp = Constants.LOGIN_REGEX)
//	String familyPhone;
//	
//	Boolean isRegister;
//	
//	// ràng buộc với hợp đồng nào
//	@ManyToOne(fetch = FetchType.LAZY)
//	Contract contract;
//
//}
