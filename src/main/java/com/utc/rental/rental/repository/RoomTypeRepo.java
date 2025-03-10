package com.utc.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.Room_Type;

public interface RoomTypeRepo extends JpaRepository<Room_Type, Long> {

}
