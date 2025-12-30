package com.rk.edu.dto.response;

import lombok.Data;

@Data
public class SubmitAttemptResponseDTO {
    private Long attemptId;
    private Integer correct;
    private Integer wrong;
    private Integer unattempted;
    private Double percentage;
    private Integer totalMarks;
}
