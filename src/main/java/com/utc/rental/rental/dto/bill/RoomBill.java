package com.utc.rental.rental.dto.bill;

import java.time.LocalDate;
import java.util.List;

import com.utc.rental.rental.dto.billType.BillTypeDTO;
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
public class RoomBill {
	private Long id;

	String roomNumber;

	//trạng thái hiện tại( hoạt động, đang sửa, ...)
	String status;
	
	Long cost;
	
	//có đang thuê không
	Boolean isActive;
	
	List<String> funiture;
	
	// mô tả về phòng trọ
	String description;
	
	//số người thuê hiện tại
	int number;
	
	BillTypeDTO billType;
}
