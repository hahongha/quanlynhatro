package com.utc.rental.rental.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.RoomReturnHistory;

public interface RoomReturnHistoryRepo extends JpaRepository<RoomReturnHistory, String> {
	@Query("SELECT r FROM RoomReturnHistory r WHERE r.room.id = :roomId AND r.contract.id = :contractId AND r.status = :status")
	Page<RoomReturnHistory> findByRoomIdAndcontractIdAndStatus(@Param("roomId") Long roomId, @Param("contractId") String contractId, @Param("status") String status, Pageable pageable);

	@Query("SELECT r FROM RoomReturnHistory r WHERE r.room.id = :roomId AND r.status = :status")
	Page<RoomReturnHistory> findByRoomIdAndStatus(@Param("roomId") Long roomId, @Param("status") String status, Pageable pageable);

	@Query("SELECT r FROM RoomReturnHistory r WHERE r.contract.id = :contractId AND r.status = :status")
	Page<RoomReturnHistory> findBycontractIdAndStatus(@Param("contractId") String contractId, @Param("status") String status, Pageable pageable);

	@Query("SELECT r FROM RoomReturnHistory r WHERE r.room.id = :roomId")
	Page<RoomReturnHistory> findByRoomId(@Param("roomId") Long roomId, Pageable pageable);

	@Query("SELECT r FROM RoomReturnHistory r WHERE r.contract.id = :contractId")
	Page<RoomReturnHistory> findBycontractId(@Param("contractId") String contractId, Pageable pageable);

	@Query("SELECT r FROM RoomReturnHistory r WHERE r.status = :status")
	Page<RoomReturnHistory> findByStatus(@Param("status") String status, Pageable pageable);
}
