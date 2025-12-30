package com.rk.edu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "test_instructions")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MockTestInstruction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    
    @OneToOne
    @JoinColumn(name = "mock_test_id")
    @JsonIgnore
    private MockTest mockTest;

    @OneToMany(mappedBy = "instruction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InstructionSection> sections;

    @OneToMany(mappedBy = "instruction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InstructionCategory> categories;

    @OneToMany(mappedBy = "instruction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InstructionSymbol> symbols;
}

