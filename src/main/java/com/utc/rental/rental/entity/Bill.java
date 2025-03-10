package com.utc.rental.rental.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "bill")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"room"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	String id;
	
	//tong so tien phai tra
	Long value;
	
	//han thanh toan
	LocalDate dueDate;
	
	//ngay thanh toan
	LocalDate paymentDate;
	
	//trang thai
	String status;
	
	//tong tien
	Long totalAmount;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Room room;
}
