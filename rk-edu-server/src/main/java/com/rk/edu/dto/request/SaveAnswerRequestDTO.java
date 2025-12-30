package com.rk.edu.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class SaveAnswerRequestDTO {
    private Long questionId;
    private List<Long> selectedOptionIds; 
    private Long timeSpentSeconds;
}
