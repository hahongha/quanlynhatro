package com.utc.rental.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.ContractMember;

public interface ContractMemberRepo extends JpaRepository<ContractMember, Long> {
	@Query("SELECT r FROM ContractMember r where r.contract.id = :x and r.status = :s")
	List<ContractMember> findByContractStatus(@Param("x") String id, @Param("s") String status);
}
