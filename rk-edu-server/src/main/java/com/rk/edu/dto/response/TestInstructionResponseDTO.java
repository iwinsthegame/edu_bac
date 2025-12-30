package com.rk.edu.dto.response;

import java.util.List;

import com.rk.edu.dto.request.InstructionCategoryDTO;
import com.rk.edu.dto.request.InstructionSectionDTO;
import com.rk.edu.dto.request.InstructionSymbolDTO;

import lombok.Data;

@Data
public class TestInstructionResponseDTO {
    private String title;
    private String description;

    private List<InstructionSectionDTO> sections;
    private List<InstructionCategoryDTO> categories;
    private List<InstructionSymbolDTO> symbols;
}

