package com.utc.rental.rental.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.Renter;

public interface RenterRepo extends JpaRepository<Renter, String> {
	@Query("SELECT r FROM Renter r where (r.phone like :x or r.fullName like :x or r.identification like :x) "
			+ "and r.status like :s")
	Page<Renter> search(@Param("x") String keyword,
			@Param("s") String status,Pageable pageable);
	
	Optional<Renter> findByIdentificationOrId(String identification, String id);
	
	@Query("SELECT r FROM Renter r where r.identification= :identification or r.id = id")
	Optional<Renter> findByID(String identification, String id);
	
	@Query("SELECT r FROM Renter r where r.identification = :x")
	Optional<Renter> findByIdentification(@Param("x") String identification);
	
	@Query("SELECT r FROM Renter r where r.user.userId = :x")
	Optional<Renter> findByUserId(@Param("x") String userId);
}
