package com.utc.rental.rental.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "create_at")
	@CreationTimestamp
	private LocalDate createAt;

	@Column(name = "update_at")
	private LocalDate updateAt;

	@Column(name = "create_by")
	private String createBy;
	
}
