package com.utc.rental.rental.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "contract_member_link")
@Data
@EqualsAndHashCode(callSuper = false, exclude = { "contract", "contractMember" })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractMemberLink extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
	private Long id;
	
	// ràng buộc với hợp đồng nào
	@ManyToOne(fetch = FetchType.LAZY)
	Contractt contract;

	// ràng buộc với hợp đồng nào
	@ManyToOne(fetch = FetchType.LAZY)
	ContractMember contractMember;
	
	private boolean isActive;
}
