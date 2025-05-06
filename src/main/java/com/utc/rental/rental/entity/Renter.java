package com.utc.rental.rental.entity;

import java.time.LocalDate;

import com.utc.rental.rental.config.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "renter")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"user"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Renter extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	String id;

	@Column(name = "identification", nullable = false, unique = true)
	String identification;

	Boolean isRegister;

	// so dien thoai
	@Pattern(regexp = Constants.LOGIN_REGEX)
	String phone;

	// quê quán
	@NotNull
	@Column(nullable = false)
	String placeOfOrigin;

	// nơi thường trú
	@NotNull
	@Column(nullable = false)
	String address;

	@Pattern(regexp = Constants.LOGIN_REGEX)
	String familyPhone;
	
	@Size(max = 256)
	@Column(name = "image_url", length = 256)
	// link anh
	private String imageUrl;

	// ho ten
	@Column(nullable = false)
	String fullName;

	@Column(nullable = false)
	// gioi tinh
	String gender;

	// ngay sinh
	@Column(nullable = false)
	LocalDate dob;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
	User user;
	// trang thai
	String status;

}
