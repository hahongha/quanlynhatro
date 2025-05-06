package com.utc.rental.rental.dto.bill;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillDetailDTO {
	private Long id;

	String name;
	
	Long unitPrice;
	
	int quantity;

	Long total;

	// ràng buộc với bill
	BillDTO bill;
}
