package com.utc.rental.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.Bill;
import com.utc.rental.rental.entity.BillDetail;

public interface BillDetailRepo extends JpaRepository<BillDetail, Long> {
	@Query("Select a from BillDetail a where a.bill.id = :r")
	List<BillDetail> findByBillId(@Param("r") String id);
}
