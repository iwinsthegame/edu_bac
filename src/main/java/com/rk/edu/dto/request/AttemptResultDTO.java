package com.rk.edu.dto.request;

import java.time.LocalDateTime;

import com.rk.edu.enums.AttemptStatus;

import lombok.Data;

@Data
public class AttemptResultDTO {
    private Long attemptId;
    private Long mockTestId;
    private String mockTestTitle;
    private Integer totalQuestions;
    private Integer correctCount;
    private Integer wrongCount;
    private Integer unattemptedCount;
    private Double percentage;
    private Integer totalMarks;
    private AttemptStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
