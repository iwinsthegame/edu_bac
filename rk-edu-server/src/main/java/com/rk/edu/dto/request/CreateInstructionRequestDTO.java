package com.rk.edu.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class CreateInstructionRequestDTO {

    private Long mockTestId;

    private String title;
    private String description;

    private List<InstructionSectionDTO> sections;
    private List<InstructionCategoryDTO> categories;
    private List<InstructionSymbolDTO> symbols;
}

