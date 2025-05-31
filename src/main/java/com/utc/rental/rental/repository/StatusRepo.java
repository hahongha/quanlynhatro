package com.utc.rental.rental.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.Statuss;

public interface StatusRepo extends JpaRepository<Statuss, String> {
//    Page<Status> findAllByOrderByCreatedAtDesc(Pageable pageable);
	List<Statuss> findAllByOrderByCreatedAtDesc();
}
