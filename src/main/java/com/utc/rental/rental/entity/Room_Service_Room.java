package com.utc.rental.rental.entity;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "room_service")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"service", "room"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room_Service_Room extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Room_Service service;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Room room;
	
	LocalDate startDate;
	
	LocalDate endDate;
	
	int quantity;
	
	String status;
}

