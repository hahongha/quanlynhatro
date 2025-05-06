package com.utc.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.Room;

public interface RoomRepo extends JpaRepository<Room, Long> {
	
}
