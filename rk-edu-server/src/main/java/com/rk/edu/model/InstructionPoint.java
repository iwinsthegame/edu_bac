package com.rk.edu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instruction_points")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InstructionPoint {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Integer displayOrder;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private InstructionCategory category;
}

