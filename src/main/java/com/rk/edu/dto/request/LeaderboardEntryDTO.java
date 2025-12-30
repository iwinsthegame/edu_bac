package com.rk.edu.dto.request;

import lombok.Data;

@Data
public class LeaderboardEntryDTO {
    private Long studentId;
    private String studentName;
    private Integer totalMarks;
    private Double percentage;
}
