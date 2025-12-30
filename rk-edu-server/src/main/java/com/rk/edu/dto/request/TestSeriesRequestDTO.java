package com.rk.edu.dto.request;



import lombok.Data;
import java.util.List;


@Data
public class TestSeriesRequestDTO {
    private String title;
    private Long examSubCategoryId;
    private List<MockTestRequestDTO> mockTests;
}

