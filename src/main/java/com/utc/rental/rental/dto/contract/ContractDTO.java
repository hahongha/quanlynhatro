package com.utc.rental.rental.dto.contract;


import java.time.LocalDate;

import com.utc.rental.rental.dto.RenterDTO;
import com.utc.rental.rental.dto.RoomDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractDTO {
	String id;
	
	// nguoi thue
	RenterDTO renter;
	// phong thue
	RoomDTO room;

	LocalDate startDate; // Ngày bắt đầu hợp đồng
	LocalDate endDate; // Ngày kết thúc hợp đồng
	LocalDate realEndDate; //ngày kết thúc thực tế
	Long rentalPrice; // Giá thuê/tháng
	Long deposit; //tiền cọc
	Boolean isDeposit; //da dat coc chua
	Boolean isActive; //co hiệu lực chưa
	LocalDate createAt;
	LocalDate updateAt;
}

