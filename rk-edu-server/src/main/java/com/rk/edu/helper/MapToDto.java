package com.rk.edu.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rk.edu.dto.response.MockTestResponseDTO;
import com.rk.edu.dto.response.OptionResponseDTO;
import com.rk.edu.dto.response.QuestionResponseDTO;
import com.rk.edu.model.MockTest;
import com.rk.edu.model.Question;

@Component
public class MapToDto {

	public MockTestResponseDTO MocktestResponseMapToDTO(MockTest mock) {
	    MockTestResponseDTO dto = new MockTestResponseDTO();
	    dto.setId(mock.getId());
	    dto.setTitle(mock.getTitle());
	    dto.setDurationMinutes(mock.getDurationMinutes());
	    dto.setTotalQuestions(mock.getTotalQuestions());
	    dto.setTotalMarks(mock.getTotalMarks());
	    dto.setIsActive(mock.getIsActive());
	    return dto;
	}
	
	public QuestionResponseDTO QuestionResponsemapToDTO(Question q) {
        QuestionResponseDTO dto = new QuestionResponseDTO();
        dto.setId(q.getId());
        dto.setQuestionText(q.getQuestionText());
        dto.setQuestionType(q.getQuestionType());
        dto.setSubject(q.getSubject());
        List<OptionResponseDTO> opts = q.getOptions().stream()
                .map(opt -> {
                    OptionResponseDTO od = new OptionResponseDTO();
                    od.setId(opt.getId());
                    od.setOptionKey(opt.getOptionKey());
                    od.setOptionText(opt.getOptionText());
                    od.setIsCorrect(opt.getIsCorrect());
                    return od;
                }).collect(Collectors.toList());
        dto.setOptions(opts);
        return dto;
    }

}
