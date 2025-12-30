package com.rk.edu.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.edu.model.OrderEntity;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByRazorpayOrderId(String orderId);
}

