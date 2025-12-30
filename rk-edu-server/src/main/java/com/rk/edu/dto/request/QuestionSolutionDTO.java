package com.rk.edu.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class QuestionSolutionDTO {
    private Long questionId;
    private String questionText;
    private List<OptionForAttemptDTO> options;
    private List<Long> correctOptionIds;
    private List<Long> userSelectedOptionIds;
    private Boolean isCorrect;
    private String explanation; 
}
