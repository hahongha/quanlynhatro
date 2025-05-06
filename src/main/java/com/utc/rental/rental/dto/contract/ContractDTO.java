package com.utc.rental.rental.dto.contract;


import java.time.LocalDate;

import com.utc.rental.rental.dto.renter.RenterDTO;
import com.utc.rental.rental.dto.room.RoomDTO;

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
	private String id;
	
	// nguoi thue
	private RenterDTO renter;

	private RoomDTO room;
	
	//số tháng thuê
	int month;
	

	LocalDate startDate; // Ngày bắt đầu hợp đồng
	LocalDate endDate; // Ngày kết thúc hợp đồng
	LocalDate realEndDate; //ngày kết thúc thực tế
	Long rentalPrice; // Giá thuê/tháng
	Long deposit; //tiền cọc
	Boolean isDeposit; //da dat coc chua
	String status; //co hiệu lực chưa
	LocalDate signatureDate;
}

