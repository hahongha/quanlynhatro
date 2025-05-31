package com.utc.rental.rental.repository;


import com.utc.rental.rental.entity.ContractMemberLink;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractMemberLinkRepo extends JpaRepository<ContractMemberLink, Long> {
	// Tìm tất cả theo contract.id
    List<ContractMemberLink> findByContract_Id(String contractId);

    // Tìm tất cả theo contractMember.id
    List<ContractMemberLink> findByContractMember_Id(Long contractMemberId);

    // Tìm tất cả theo contract.id hoặc contractMember.id
    List<ContractMemberLink> findByContract_IdOrContractMember_Id(String contractId, Long contractMemberId);
}

