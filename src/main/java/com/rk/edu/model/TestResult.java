package com.rk.edu.model;



import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "test_results")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TestResult {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer correctCount;
    private Integer wrongCount;
    private Integer totalMarks;
    private Double percentage;
   

    @OneToOne
    @JoinColumn(name = "attempt_id")
    private StudentAttempt attempt;
}

