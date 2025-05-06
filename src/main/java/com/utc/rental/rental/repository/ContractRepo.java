package com.utc.rental.rental.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.Contractt;

public interface ContractRepo extends JpaRepository<Contractt, String> {
	@Query("SELECT r FROM Contractt r")
	Page<Contractt> searchContract(@Param("keyword") String keyword, Pageable pageable);
	
//	@Query("SELECT r FROM Contract r where r.renter.id = :x and r.status = :s")
//	Page<Contractt> searchByRenterStatus(@Param("x") String id,@Param("s") String status, Pageable pageable);
//	
	@Query("SELECT r FROM Contractt r where r.renter.id = :x and r.status = :s")
	List<Contractt> findByRenterStatus(@Param("x") String id,@Param("s") String status);
	
	@Query("SELECT r FROM Contractt r where r.room.id = :x and r.status = :s")
	List<Contractt> findByRoomStatus(@Param("x") String id,@Param("s") String status);
//	
	@Query("SELECT r FROM Contractt r where r.renter.id = :x")
	List<Contractt> findByRenterId(@Param("x") String id);
}
