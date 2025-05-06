package com.utc.rental.rental.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "bill")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"room", "renter", "billDetails"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	String id;
	
	String name;
	
	//tong so tien phai tra
	Long value;
	
	//han thanh toan
	LocalDate dueDate;
	
	//ngay thanh toan
	LocalDate paymentDate;
	
	Long discount;
	
	Long paid;
	
	//trang thai
	String status;
	
	//tong tien
	Long totalAmount;
	
	String note;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "room_id", nullable = true)
    private Room room;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "renter_id", nullable = true)
    private Renter renter;
	
	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillDetail> billDetails;
}
