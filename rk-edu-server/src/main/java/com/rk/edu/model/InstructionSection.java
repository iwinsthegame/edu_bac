package com.rk.edu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instruction_sections")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InstructionSection {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer totalQuestions;
    private Integer maxScore;
    private Double correctMarks;
    private Double negativeMarks;

    @ManyToOne
    @JoinColumn(name = "instruction_id")
    @JsonIgnore
    private MockTestInstruction instruction;
}

