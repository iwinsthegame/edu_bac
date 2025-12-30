package com.rk.edu.dto.request;



import com.rk.edu.enums.ExamStage;

import lombok.Data;

@Data
public class PyqPaperRequestDTO {

    private String title;
    private Integer year;
    private String examName;
    private ExamStage examStage;

    private Long examCategoryId;
    private Long examSubCategoryId;
}

