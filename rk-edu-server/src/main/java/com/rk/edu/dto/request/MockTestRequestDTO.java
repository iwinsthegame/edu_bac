package com.rk.edu.dto.request;


import lombok.Data;

@Data
public class MockTestRequestDTO {
    private String title;
    private Integer durationMinutes; 
    private Integer totalQuestions;
    private Integer totalMarks;
    private Boolean isActive;
}
