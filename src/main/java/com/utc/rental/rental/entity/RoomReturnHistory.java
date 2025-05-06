package com.utc.rental.rental.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "room_return")
@Data
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomReturnHistory extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contractt contract;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate returnDate;
    
    private String note;
    
    private String reason;
    
    private String status;
}
