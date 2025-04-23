package com.utc.rental.rental.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.utc.rental.rental.config.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = false, exclude = { "role" })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
	@NotNull
	String userId;

	@Column(nullable = false, unique = true)
	String userName;

	@JsonIgnore
	@NotNull
	@Size(min = 60, max = 60)
	@Column(name = "password_hash", length = 60, nullable = false)
	String password;

	String status;

	@Email
	@Size(min = 5, max = 254)
	@Column(length = 254, unique = true)
	private String email;

	@Size(max = 256)
	@Column(name = "image_url", length = 256)
	// link anh
	private String imageUrl;

	@Column(name = "create_by")
	private String createBy;

	// ho ten
//	@NotNull
	@Column(nullable = false)
	String fullName;

//	@NotNull
	@Column(nullable = false)
	// gioi tinh
	String gender;

	// so dien thoai
	@Pattern(regexp = Constants.LOGIN_REGEX)
	String phone;

	// ngay sinh
	@NotNull
	@Column(nullable = false)
	LocalDate dob;

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

	@ManyToOne
	@JoinColumn(name = "role_id")
	Role role;

    @Column(name = "note")
    String note;
	
}
