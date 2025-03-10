package com.utc.rental.rental.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.utc.rental.rental.config.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "create_at")
	@CreationTimestamp
	private LocalDate createAt;

	@Column(name = "update_at")
	private LocalDate updateAt;

	@Column(name = "create_by")
	private String createBy;
	
	// ho ten
	@NotNull
	@Column(nullable = false)
	String fullName;

	@NotNull
	@Column(nullable = false)
	// gioi tinh
	String gender;

	@NotNull
	@Column(nullable = false)
	// trang thai
	String status;
	
	// so dien thoai
	@Pattern(regexp = Constants.LOGIN_REGEX)
	String phone;

	// ngay sinh
	@NotNull
	@Column(nullable = false)
	LocalDate dob;

	// cccd
	@NotNull
	@Pattern(regexp = Constants.ID_REGEX)
	@Column(nullable = false, unique = true)
	String identification;
	
	//quê quán
	@NotNull
	@Column(nullable = false)
	String placeOfOrigin;
	
	//nơi thường trú
	@NotNull
	@Column(nullable = false)
	String address;
	
	@Pattern(regexp = Constants.LOGIN_REGEX)
	String familyPhone;

	// dang ki tam tru chua
	@NotNull
	@Column(nullable = false)
	Boolean isRegister;
}

