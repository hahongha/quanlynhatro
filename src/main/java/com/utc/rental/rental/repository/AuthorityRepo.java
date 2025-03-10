package com.utc.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utc.rental.rental.entity.Authority;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, String> {
	
}
