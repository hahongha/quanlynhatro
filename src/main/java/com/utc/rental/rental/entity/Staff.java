package com.utc.rental.rental.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "staff")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"user"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Staff extends Person {
private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	String id;

	@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true) 
	User user;
}
