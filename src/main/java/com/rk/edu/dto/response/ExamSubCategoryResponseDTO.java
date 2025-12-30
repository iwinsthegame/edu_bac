package com.rk.edu.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ExamSubCategoryResponseDTO {
	
	private Long id;
    private String name;
    private String description;
    private List<TestSeriesResponseDTO> testSeriesList;
    

}
