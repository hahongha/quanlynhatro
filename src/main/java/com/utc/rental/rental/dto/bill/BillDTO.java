package com.utc.rental.rental.dto.bill;

import java.time.LocalDate;
import java.util.List;

import com.utc.rental.rental.dto.billType.BillTypeDTO;
import com.utc.rental.rental.dto.renter.RenterDTO;
import com.utc.rental.rental.dto.room.RoomDTO;
import com.utc.rental.rental.entity.BillType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillDTO {
	String id;
	
	String name;
	
	//tong so tien phai tra
	Long value;
	
	//han thanh toan
	LocalDate dueDate;
	
	//ngay thanh toan
	LocalDate paymentDate;
	
	//trang thai
	String status;
	
	Long discount;
	
	Long paid;
	
	String note;
	
	//tong tien
	Long totalAmount;
	
    private RoomBill room;
	
    private RenterBill renter;
    
    LocalDate createAt;
    
    LocalDate updateAt;
    
    List<BillDetailResponse> billDetails;
    
    BillTypeDTO billType;
}
