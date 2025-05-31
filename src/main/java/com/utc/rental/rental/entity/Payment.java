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
@Table(name = "payment")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"bill"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
	@NotNull
	String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Bill bill;
	
	//phương thức thanh toán
	String paymentMethod;
	
	//ngày thanh toán
	LocalDate paymentDate;
	
	//ghi chú thêm
	String note;
	
	//số tiền đã thanh toán
	Long value;
}
