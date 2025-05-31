package com.utc.rental.rental.dto.cmlink;


import com.utc.rental.rental.dto.contract.ContractResponseDTO;
import com.utc.rental.rental.dto.contractMember.ContractMemberDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CMLinkDTO {
	Long id;
	ContractResponseDTO contract;
	ContractMemberDTO contractMember;
	boolean isActive;
}
