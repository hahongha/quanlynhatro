package com.utc.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.Staff;

public interface StaffRepo extends JpaRepository<Staff, String> {

}
