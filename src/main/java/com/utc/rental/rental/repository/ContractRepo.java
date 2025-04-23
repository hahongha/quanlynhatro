//package com.utc.rental.rental.repository;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.utc.rental.rental.entity.Contract;
//
//public interface ContractRepo extends JpaRepository<Contract, String> {
//	@Query("SELECT r FROM Contract r")
//	Page<Contract> search(@Param("keyword") String keyword, Pageable pageable);
//}
