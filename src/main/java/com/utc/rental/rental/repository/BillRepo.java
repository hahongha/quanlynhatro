package com.utc.rental.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.Bill;

public interface BillRepo extends JpaRepository<Bill, String> {
	@Query("Select a from Bill a where a.renter.id = :r")
	List<Bill> findByRenterId(@Param("r") String renterId);
}
