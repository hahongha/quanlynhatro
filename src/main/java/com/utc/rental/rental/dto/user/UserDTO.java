package com.utc.rental.rental.dto.user;

import java.time.Instant;
import java.time.LocalDate;

import com.utc.rental.rental.dto.role.RoleDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
	private String userId;
	private String userName;
	private String status;
	private String email;
	private String imageUrl;
	private String fullName;
	private String gender;
	private String phone;
	private LocalDate dob;
	private String placeOfOrigin;
	private String address;
	private String familyPhone;
	private String note;
	private RoleDTO role;

}
