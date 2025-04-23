package com.utc.rental.rental.entity;

import java.time.LocalDate;

import com.utc.rental.rental.config.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "renter")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"user"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Renter extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	String id;
	
	@Column(nullable = false, unique = true)
	String identification;
	
	Boolean isRegister;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
	User user;

}
