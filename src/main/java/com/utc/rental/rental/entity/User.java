package com.utc.rental.rental.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"role"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
	@NotNull
	String userId;

	@Column(nullable = false, unique = true)
	String userName;
	
	String email;

	@JsonIgnore
	@NotNull
	@Size(min = 60, max = 60)
	@Column(name = "password_hash", length = 60, nullable = false)
	String password;

	String status;

	@ManyToOne
	@JoinColumn(name = "role_id")
	Role role;
	
	String userType;
	
	String userAvatar;
	
}
