package com.rk.edu.dto.request;

import lombok.Data;

@Data
public class InstructionSectionDTO {
    private String name;
    private Integer totalQuestions;
    private Integer maxScore;
    private Double correctMarks;
    private Double negativeMarks;
}

