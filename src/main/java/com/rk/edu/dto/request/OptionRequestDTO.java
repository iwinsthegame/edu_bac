package com.rk.edu.dto.request;

import lombok.Data;

@Data
public class OptionRequestDTO {
    private String optionKey;   
    private String optionText;
    private Boolean isCorrect;  
}
