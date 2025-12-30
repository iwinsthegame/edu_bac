package com.rk.edu.dto.request;

import java.util.List;

import com.rk.edu.enums.QuestionType;

import lombok.Data;


@Data
public class QuestionForAttemptDTO {
 private Long questionId;
 private String questionText;
 private QuestionType questionType;
 private String subject;
 private List<OptionForAttemptDTO> options;
}

