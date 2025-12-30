package com.rk.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.edu.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}

