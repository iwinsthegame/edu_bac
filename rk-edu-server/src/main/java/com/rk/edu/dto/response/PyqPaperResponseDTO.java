package com.rk.edu.dto.response;


import com.rk.edu.enums.ExamStage;

import lombok.Data;

@Data
public class PyqPaperResponseDTO {

    private Long id;
    private String title;
    private Integer year;
    private String examName;
    private String pdfUrl;
    private ExamStage examStage;

    private String examCategory;
    private String examSubCategory;
}
