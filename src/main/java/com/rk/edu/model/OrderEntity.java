package com.rk.edu.model;


import com.rk.edu.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class OrderEntity {

    @Id @GeneratedValue
    private Long id;

    private String razorpayOrderId;
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;
}

