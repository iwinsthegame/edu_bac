package com.rk.edu.dto.response;


import lombok.Data;
import java.util.List;



@Data
public class TestSeriesResponseDTO {
    private Long id;
    private String title;
    private String examCategory;
    private String examSubCategory;
    private List<MockTestResponseDTO> mockTests;
    private Long createdByAdminId; 
}

