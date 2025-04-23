package com.utc.rental.rental.dto.user;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateDTO {
	private String email;
	private String imageUrl;
	private String phone;
	private String familyPhone;
	private String userName;
	private String userId;
}
