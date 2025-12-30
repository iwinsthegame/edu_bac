package com.rk.edu.dto.response;

import lombok.Data;

@Data
public class OptionResponseDTO {
    private Long id;
    private String optionKey;
    private String optionText;
    private Boolean isCorrect;
}
