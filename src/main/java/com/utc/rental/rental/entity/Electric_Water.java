package com.utc.rental.rental.entity;


import java.time.LocalDate;

import org.hibernate.annotations.Formula;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "electric_water")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"room"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Electric_Water extends BaseModel{
	private static final long serialVersionUID = 1L;
    @Id
    private String id;

    private String type;

    private int month;

    private int year;

    private double previousIndex;

    private double currentIndex;

    private Long pricePerUnit;
    
    private LocalDate recordDate;

    @Formula("current_index - previous_index")
    private double value;

    @Formula("(current_index - previous_index) * price_per_unit")
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
