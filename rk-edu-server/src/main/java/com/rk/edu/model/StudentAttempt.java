package com.rk.edu.model;



import java.time.LocalDateTime;

import com.rk.edu.enums.AttemptStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_attempts")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StudentAttempt {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "mock_test_id")
    private MockTest mockTest;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer totalQuestions;
    private Integer correctCount;
    private Integer wrongCount;
    private Integer unattemptedCount;

    private Double percentage;
    private Integer totalMarks;

    // attempt status: STARTED, SUBMITTED, TIME_UP
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private AttemptStatus status;
}

