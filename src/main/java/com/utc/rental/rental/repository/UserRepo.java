package com.utc.rental.rental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.User;

public interface UserRepo extends JpaRepository<User, String> {
	Optional<User> findByUserName(String username);
}
