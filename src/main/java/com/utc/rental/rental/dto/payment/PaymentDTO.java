package com.utc.rental.rental.dto.payment;

import java.time.LocalDate;

import com.utc.rental.rental.dto.bill.BillDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDTO {
	String id;
	
	BillDTO bill;
	
	//phương thức thanh toán
	String paymentMethod;
	
	//ngày thanh toán
	LocalDate paymentDate;
	
	//ghi chú thêm
	String note;
	
	//số tiền đã thanh toán
	Long value;
	
	LocalDate createAt;
	
	LocalDate updateAt;
}
