//package com.utc.rental.rental.entity;
//
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.experimental.FieldDefaults;
//
//@Entity
//@Table(name = "room_type")
//@Data
//@EqualsAndHashCode(callSuper = false)
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class RoomType extends BaseModel {
//	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
//	Long id;
//	
//	String name;
//	
//	Long size;
//	
//	String furniture;
//	
//	String description;
//	
//	//giá tiền
//	Long cost;
//}
