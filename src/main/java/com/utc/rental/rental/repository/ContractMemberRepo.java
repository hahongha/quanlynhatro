package com.utc.rental.rental.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.ContractMember;

public interface ContractMemberRepo extends JpaRepository<ContractMember, Long> {
	Optional<ContractMember> findByIdentification(String identification);
}
