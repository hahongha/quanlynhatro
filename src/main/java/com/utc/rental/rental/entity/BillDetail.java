//package com.utc.rental.rental.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.experimental.FieldDefaults;
//
//@Entity
//@Table(name = "bill_detail")
//@Data
//@EqualsAndHashCode(callSuper = false, exclude = {"bill"})
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class BillDetail extends BaseModel {
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
//	private Long id;
//
//	@NotNull
//	String name;
//
//	Long discount;
//
//	Long value;
//
//	// ràng buộc với bill
//	@ManyToOne(fetch = FetchType.LAZY)
//	Bill bill;
//}
