package com.rk.edu.dto.response;



import lombok.Data;

@Data
public class MockTestResponseDTO {
    private Long id;
    private String title;
    private Integer durationMinutes;
    private Integer totalQuestions;
    private Integer totalMarks;
    private Boolean isActive;
}

