package com.rk.edu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instruction_symbols")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InstructionSymbol {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "instruction_id")
    @JsonIgnore
    private MockTestInstruction instruction;
}

