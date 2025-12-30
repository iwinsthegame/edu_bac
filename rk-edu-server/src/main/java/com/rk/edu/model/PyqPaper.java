package com.rk.edu.model;


import com.rk.edu.enums.ExamStage;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pyq_papers")
@Data
public class PyqPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer year;

    private String examName; // SSC, UPSC, RRB etc
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExamStage examStage; 

    @Column(nullable = false)
    private String pdfUrl;

    @ManyToOne
    @JoinColumn(name = "exam_category_id")
    private ExamCategory examCategory;

    @ManyToOne
    @JoinColumn(name = "exam_subcategory_id")
    private ExamSubCategory examSubCategory;
}
