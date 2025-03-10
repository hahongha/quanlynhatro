package com.utc.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
