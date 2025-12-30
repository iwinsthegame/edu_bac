package com.rk.edu.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "student_answers")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StudentAnswer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long selectedOptionId;      
    private Boolean isCorrect;          

    @ManyToOne
    @JoinColumn(name = "attempt_id")
    private StudentAttempt attempt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}

