package com.utc.rental.rental.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.RoomType;
import com.utc.rental.rental.entity.Room_Service_Room;

public interface ServiceRoomRepo extends JpaRepository<Room_Service_Room, Long> {
//	@Query("SELECT r FROM Room_Service_Room r where r.room.id = :x and r.service.id = :s and r.status = :l ")
//	Page<Room_Service_Room> search(@Param("x") Long roomId, @Param("y")Long serviceId, @Param("l")String status,
//			Pageable pageable);
//	@Query("SELECT r FROM Room_Service_Room r where r.room.id = :x and r.status = :l ")
//	Page<Room_Service_Room> search2(@Param("x") Long roomId, @Param("l")String status,
//			Pageable pageable);
//	@Query("SELECT r FROM Room_Service_Room r where r.service.id = :s and r.status = :l ")
//	Page<Room_Service_Room> search3(@Param("y")Long serviceId, @Param("l")String status,
//			Pageable pageable);
//	@Query("SELECT r FROM Room_Service_Room r where r.room.id = :x")
//	Page<Room_Service_Room> search1(@Param("x") Long roomId,
//			Pageable pageable);
//	@Query("SELECT r FROM Room_Service_Room r where r.room.id = :x and r.service.id = :s and r.status = :l ")
//	Page<Room_Service_Room> search4(@Param("l")String status,
//			Pageable pageable);
//	@Query("SELECT r FROM Room_Service_Room r where r.room.id = :x and r.service.id = :s and r.status = :l ")
//	Page<Room_Service_Room> search5(@Param("y")Long serviceId,
//			Pageable pageable);
	@Query("SELECT r FROM Room_Service_Room r WHERE r.room.id = :roomId AND r.service.id = :serviceId AND r.status = :status")
	Page<Room_Service_Room> findByRoomIdAndServiceIdAndStatus(@Param("roomId") Long roomId, @Param("serviceId") Long serviceId, @Param("status") String status, Pageable pageable);

	@Query("SELECT r FROM Room_Service_Room r WHERE r.room.id = :roomId AND r.status = :status")
	Page<Room_Service_Room> findByRoomIdAndStatus(@Param("roomId") Long roomId, @Param("status") String status, Pageable pageable);

	@Query("SELECT r FROM Room_Service_Room r WHERE r.service.id = :serviceId AND r.status = :status")
	Page<Room_Service_Room> findByServiceIdAndStatus(@Param("serviceId") Long serviceId, @Param("status") String status, Pageable pageable);

	@Query("SELECT r FROM Room_Service_Room r WHERE r.room.id = :roomId")
	Page<Room_Service_Room> findByRoomId(@Param("roomId") Long roomId, Pageable pageable);

	@Query("SELECT r FROM Room_Service_Room r WHERE r.service.id = :serviceId")
	Page<Room_Service_Room> findByServiceId(@Param("serviceId") Long serviceId, Pageable pageable);

	@Query("SELECT r FROM Room_Service_Room r WHERE r.status = :status")
	Page<Room_Service_Room> findByStatus(@Param("status") String status, Pageable pageable);

}
