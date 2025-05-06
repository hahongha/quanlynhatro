package com.utc.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, String> {

}
