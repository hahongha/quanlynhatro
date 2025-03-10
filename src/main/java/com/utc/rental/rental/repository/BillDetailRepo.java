package com.utc.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.BillDetail;

public interface BillDetailRepo extends JpaRepository<BillDetail, Long> {

}
