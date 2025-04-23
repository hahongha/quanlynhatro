package com.utc.rental.rental.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "authority")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"roles"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Authority extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    private Long id;
	
	@Column(nullable = false, unique = true)
	String name;
	
	String description;
	
	@ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<Role>();

}
