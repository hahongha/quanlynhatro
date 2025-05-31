package com.utc.rental.rental.dto.staff;

import java.time.LocalDate;

import com.utc.rental.rental.dto.authority.AuthorityDTO;
import com.utc.rental.rental.dto.user.UserDTO;
import com.utc.rental.rental.dto.user.UserResponse;
import com.utc.rental.rental.entity.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffDTO {
	String id;

	String position;

//	String email;

	// so dien thoai
	String phone;

//    // Ca làm việc: sáng, chiều, tối (tùy phân ca)
//    @Column(name = "work_shift")
//    String workShift;
//
//    // Mức lương (nếu cần)
//    @Column(name = "salary")
//    Double salary;

//  Ngày bắt đầu làm việc
	LocalDate startDate;

	LocalDate end_date;

	private String imageUrl;

	// ho ten
	String fullName;

	// gioi tinh
	String gender;

	// ngay sinh
	LocalDate dob;

	String note;
	
	private LocalDate createAt;

	
	private LocalDate updateAt;
	
	UserDTO user;
}
