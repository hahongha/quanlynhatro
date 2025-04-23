package com.utc.rental.rental.dto.user;

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
public class UserResponse {
	String imageUrl;
	String email;
	String userName;
	RoleDTO role;
	String status;
}
