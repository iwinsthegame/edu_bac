package com.rk.edu.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ExamCategoryResponseDTO {

	private Long id;
    private String name;
    private String description;
    private String logoUrl;
    private List<ExamSubCategoryResponseDTO> subCategories;
}
