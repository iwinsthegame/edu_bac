package com.rk.edu.services.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rk.edu.dto.response.QuestionResponseDTO;
import com.rk.edu.helper.MapToDto;
import com.rk.edu.model.Question;
import com.rk.edu.repositories.MockTestRepository;
import com.rk.edu.repositories.OptionRepository;
import com.rk.edu.repositories.QuestionRepository;
import com.rk.edu.repositories.TestSeriesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQuestionService {
	
	 private final MockTestRepository mockTestRepository;
	 private final QuestionRepository questionRepository;
	 private final OptionRepository optionRepository;
	 private final MapToDto mapToDto;
	
	public QuestionResponseDTO getQuestion(Long id) {
        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return mapToDto.QuestionResponsemapToDTO(q);
    }

    // Get all questions for a mock test
    public List<QuestionResponseDTO> getQuestionsByMockTest(Long mockTestId) {
        List<Question> list = questionRepository.findByMockTestId(mockTestId);
        return list.stream().map(mapToDto::QuestionResponsemapToDTO).collect(Collectors.toList());
    }

}
