package com.utc.rental.rental.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "contract")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"renter", "room"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contractt extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	private String id;
	
	// nguoi thue
	@ManyToOne(fetch = FetchType.EAGER)
	private Renter renter;
	// phong thue
	@ManyToOne(fetch = FetchType.EAGER)
	private Room room;
	
	//số tháng thuê
	int month;

	LocalDate startDate; // Ngày bắt đầu hợp đồng
	LocalDate endDate; // Ngày kết thúc hợp đồng
	LocalDate realEndDate; //ngày kết thúc thực tế
	Long rentalPrice; // Giá thuê/tháng
	Long deposit; //tiền cọc
	Boolean isDeposit; //da dat coc chua
	String status; //co hiệu lực chưa
	LocalDate signatureDate;// ngày kí hợp đồng
}
