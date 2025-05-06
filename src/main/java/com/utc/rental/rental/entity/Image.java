//package com.utc.rental.rental.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.experimental.FieldDefaults;
//
//@Entity
//@Table(name = "image")
//@Data
//@EqualsAndHashCode(callSuper = false)
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@NoArgsConstructor
//@AllArgsConstructor
//public class Image extends BaseModel {
//	private static final long serialVersionUID = 1L;
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
//    private Long id;
//	
//	String imageName;
//	
//	String imageURL;
//	
//	String cloudinaryId;
//		
//	//loại ảnh
//	String imageType;
//	
//	//ảnh phòng ốc liên quan đến ảnh đó
//	String idReference;
//	
//	
//}
