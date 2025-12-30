package com.rk.edu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instruction_categories")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InstructionCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer displayOrder;

    @ManyToOne
    @JoinColumn(name = "instruction_id")
    @JsonIgnore
    private MockTestInstruction instruction;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InstructionPoint> points;
}

