package com.utc.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.BillType;
import com.utc.rental.rental.entity.Complaint;

public interface BillTypeRepo extends JpaRepository<BillType, String> {

}
