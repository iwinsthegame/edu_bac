package com.rk.edu.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class InstructionCategoryDTO {
    private String title;
    private Integer displayOrder;
    private List<InstructionPointDTO> points;
}

