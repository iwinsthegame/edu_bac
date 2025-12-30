package com.rk.edu.dto.request;

import lombok.Data;

@Data
public class SubjectAnalyticsDTO {
    private String subject;
    private Integer totalQuestions;
    private Integer correct;
    private Integer wrong;
    private Double accuracy;
}
