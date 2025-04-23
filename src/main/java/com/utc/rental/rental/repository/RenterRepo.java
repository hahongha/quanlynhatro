package com.utc.rental.rental.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.Renter;

public interface RenterRepo extends JpaRepository<Renter, String> {
	@Query("SELECT r FROM Renter r")
	Page<Renter> search(@Param("keyword") String keyword, Pageable pageable);
	
	Optional<Renter> findByIdentificationOrId(String identification, String id);
	Optional<Renter> findByIdentification(String identification);
	@Query("SELECT r FROM Renter r where r.user.userId = :x")
	Optional<Renter> findByUserId(@Param("x") String userId);
}
