package com.utc.rental.rental.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "electric_water")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"room"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Electric_Water extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Room room;
	
	//tháng áp dụng
	LocalDate date;
	
	//số điện
	double electricNumber;
	
	//số nước
	double waterNumber;
	
	//giá điện
	Long electricValue;
	
	//giá nước
	Long waterValue;
	
}
