package com.rk.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.edu.model.MockTest;

@Repository
public interface MockTestRepository extends JpaRepository<MockTest, Long> {}

