package com.utc.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.Renter;

public interface RenterRepo extends JpaRepository<Renter, String> {

}
