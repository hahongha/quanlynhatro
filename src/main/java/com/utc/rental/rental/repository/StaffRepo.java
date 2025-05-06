package com.utc.rental.rental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.Staff;

public interface StaffRepo extends JpaRepository<Staff, String> {
	@Query("SELECT a from Staff a where a.user.userId = :x")
	Optional<Staff>findByUserId(@Param("x") String userId);
}
