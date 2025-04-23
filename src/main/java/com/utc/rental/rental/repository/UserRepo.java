package com.utc.rental.rental.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.User;

public interface UserRepo extends JpaRepository<User, String> {
	Optional<User> findByUserName(String username);
	
	@Query("SELECT r FROM User r WHERE r.userName LIKE %:x% or r.email LIKE %:x%")
	Page<User> search(@Param("x") String keyword, Pageable pageable);
}
