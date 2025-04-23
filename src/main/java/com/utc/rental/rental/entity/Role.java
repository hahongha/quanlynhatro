package com.utc.rental.rental.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
@Entity
@Table(name = "role")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"authorities",  "users"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    private Long id;
	
	@Column(unique = true, nullable = false)
	private String roleName;
	
	private String roleDes;
	
	@JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "role_authority",
        joinColumns = { 
        		@JoinColumn(name = "role_id", referencedColumnName = "id")		
        },
        inverseJoinColumns = { 
        		@JoinColumn(name = "authority_id", referencedColumnName = "id")
        }
    )
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<Authority>();
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users;
}
