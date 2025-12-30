package com.rk.edu.dto.response;

import java.util.List;

import com.rk.edu.dto.request.QuestionForAttemptDTO;

import lombok.Data;

@Data
public class StartAttemptResponseDTO {
    private Long attemptId;
    private Long mockTestId;
    private String mockTestTitle;
    private Integer durationMinutes;
    private List<QuestionForAttemptDTO> questions;
}
