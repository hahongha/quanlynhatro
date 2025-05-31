package com.utc.rental.rental.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, String> {
	@Query("""
		    SELECT p FROM Payment p
		    WHERE (:paymentMethod IS NULL OR LOWER(p.paymentMethod) = LOWER(:paymentMethod))
		      AND (:startDate IS NULL OR p.paymentDate >= :startDate)
		      AND (:endDate IS NULL OR p.paymentDate <= :endDate)
		      AND (:status IS NULL OR p.bill.status = :status)
		      AND (:minAmount IS NULL OR p.value >= :minAmount)
		      AND (:maxAmount IS NULL OR p.value <= :maxAmount)
		    ORDER BY p.updateAt DESC
		""")
		Page<Payment> searchPayments(
		    @Param("paymentMethod") String paymentMethod,
		    @Param("startDate") LocalDate startDate,
		    @Param("endDate") LocalDate endDate,
		    @Param("status") String status,
		    @Param("minAmount") Long minAmount,
		    @Param("maxAmount") Long maxAmount,
		    Pageable pageable
		);


}
