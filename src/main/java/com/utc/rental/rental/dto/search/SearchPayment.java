package com.utc.rental.rental.dto.search;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchPayment {
	SearchDTO searchDTO;
	String paymentMethod;
    LocalDate startDate;
    LocalDate endDate;
    String status;
    Long minAmount;
    Long maxAmount;
}
