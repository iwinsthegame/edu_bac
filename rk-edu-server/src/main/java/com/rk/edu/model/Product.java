package com.rk.edu.model;

import com.rk.edu.enums.ProductType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ProductType type;
}

