package com.rk.edu.dto.request;

import lombok.Data;
import java.util.List;

import com.rk.edu.enums.QuestionType;

@Data
public class QuestionRequestDTO {
    private String questionText;
    private QuestionType questionType;
    private String subject;                
    private List<OptionRequestDTO> options; 
}