package com.utc.rental.rental.entity;

import java.time.LocalDate;

import com.utc.rental.rental.config.Constants;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, exclude = {"user"})
public class Staff extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Id
	String id;

	// Liên kết với bảng user
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	User user;

	// Chức vụ: nhân viên quản lý, tiếp tân, v.v.
	@Column(name = "position", nullable = false)
	String position;

//	@Email
//	@Size(min = 5, max = 254)
//	@Column(length = 254)
//	private String email;

	// so dien thoai
	@Pattern(regexp = Constants.LOGIN_REGEX)
	String phone;

//    // Ca làm việc: sáng, chiều, tối (tùy phân ca)
//    @Column(name = "work_shift")
//    String workShift;
//
//    // Mức lương (nếu cần)
//    @Column(name = "salary")
//    Double salary;

//  Ngày bắt đầu làm việc
	@Column(name = "start_date", nullable = false)
	LocalDate startDate;

//  Ngày kết thúc làm việc
	@Column(name = "end_date")
	LocalDate end_date;

	@Size(max = 256)
	@Column(name = "image_url", length = 256)
	// link anh
	private String imageUrl;

	// ho ten
//	@NotNull
	@Column(nullable = false)
	String fullName;

//	@NotNull
	@Column(nullable = false)
	// gioi tinh
	String gender;

	// ngay sinh
	@NotNull
	@Column(nullable = false)
	LocalDate dob;

	@Column(name = "note")
	String note;

}
