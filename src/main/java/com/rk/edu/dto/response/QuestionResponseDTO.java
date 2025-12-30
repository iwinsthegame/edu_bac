package com.rk.edu.dto.response;

import lombok.Data;
import java.util.List;

import com.rk.edu.enums.QuestionType;

@Data
public class QuestionResponseDTO {
    private Long id;
    private String questionText;
    private QuestionType questionType;
    private String subject;
    private List<OptionResponseDTO> options;
}