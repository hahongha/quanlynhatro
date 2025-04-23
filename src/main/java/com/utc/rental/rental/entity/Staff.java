package com.utc.rental.rental.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Staff {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // Liên kết với bảng user
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    User user;

    // Chức vụ: nhân viên quản lý, tiếp tân, v.v.
    @Column(name = "position", nullable = false)
    String position;

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
	@Column(name = "end_date", nullable = false)
	LocalDate end_date;
    
}
