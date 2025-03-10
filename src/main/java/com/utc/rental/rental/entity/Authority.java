package com.utc.rental.rental.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "authority")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"roles"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Authority {
	@Id
	@NotNull
	String id;
	
	@NotNull
	String name;
	
	@ManyToMany(mappedBy = "authorities")
    private Set<Role> roles = new HashSet<Role>();

}
