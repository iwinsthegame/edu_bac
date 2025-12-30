package com.rk.edu.services.admin;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rk.edu.dto.request.OptionRequestDTO;
import com.rk.edu.dto.request.QuestionRequestDTO;
import com.rk.edu.dto.response.QuestionResponseDTO;
import com.rk.edu.enums.QuestionType;
import com.rk.edu.helper.MapToDto;
import com.rk.edu.model.MockTest;
import com.rk.edu.model.Option;
import com.rk.edu.model.Question;
import com.rk.edu.repositories.MockTestRepository;
import com.rk.edu.repositories.OptionRepository;
import com.rk.edu.repositories.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminQuestionService {
	
	private final MockTestRepository mockTestRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final MapToDto mapToDto;
	
	// Create question with options
    @Transactional
    public QuestionResponseDTO createQuestion(Long mockTestId, QuestionRequestDTO req) {
        MockTest mock = mockTestRepository.findById(mockTestId)
                .orElseThrow(() -> new RuntimeException("MockTest not found"));

        validateRequest(req);

        Question question = Question.builder()
                .questionText(req.getQuestionText())
                .questionType(req.getQuestionType())
                .subject(req.getSubject())
                .mockTest(mock)
                .build();

        // attach options
        if (req.getOptions() != null) {
            int idx = 0;
            for (OptionRequestDTO oReq : req.getOptions()) {
                Option option = Option.builder()
                        .optionKey(oReq.getOptionKey() != null ? oReq.getOptionKey() : generateKey(idx))
                        .optionText(oReq.getOptionText())
                        .isCorrect(Boolean.TRUE.equals(oReq.getIsCorrect()))
                        .question(question)
                        .build();
                question.getOptions().add(option);
                idx++;
            }
        }

        Question saved = questionRepository.save(question); // cascade saves options
        return mapToDto.QuestionResponsemapToDTO(saved);
    }
    
    //list of question add 
    @Transactional
    public List<QuestionResponseDTO> createQuestions(
            Long mockTestId,
            List<QuestionRequestDTO> requests) {

        MockTest mock = mockTestRepository.findById(mockTestId)
                .orElseThrow(() -> new RuntimeException("MockTest not found"));

        if (requests == null || requests.isEmpty()) {
            throw new RuntimeException("Question list cannot be empty");
        }

        return requests.stream().map(req -> {

            validateRequest(req);

            Question question = Question.builder()
                    .questionText(req.getQuestionText())
                    .questionType(req.getQuestionType())
                    .subject(req.getSubject())
                    .mockTest(mock)
                    .build();

            // attach options
            if (req.getOptions() != null) {
                int idx = 0;
                for (OptionRequestDTO oReq : req.getOptions()) {
                    Option option = Option.builder()
                            .optionKey(
                                oReq.getOptionKey() != null
                                    ? oReq.getOptionKey()
                                    : generateKey(idx)
                            )
                            .optionText(oReq.getOptionText())
                            .isCorrect(Boolean.TRUE.equals(oReq.getIsCorrect()))
                            .question(question)
                            .build();

                    question.getOptions().add(option);
                    idx++;
                }
            }

            Question saved = questionRepository.save(question);
            return mapToDto.QuestionResponsemapToDTO(saved);

        }).toList();
    }

    
    
    
   
 // Update question (replace options)
    @Transactional
    public QuestionResponseDTO updateQuestion(Long id, QuestionRequestDTO req) {
        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        validateRequest(req);

        q.setQuestionText(req.getQuestionText());
        q.setQuestionType(req.getQuestionType());
        q.setSubject(req.getSubject());

        // Replace options: remove existing and add new ones (or you can implement diff)
        q.getOptions().clear();
        if (req.getOptions() != null) {
            int idx = 0;
            for (OptionRequestDTO oReq : req.getOptions()) {
                Option option = Option.builder()
                        .optionKey(oReq.getOptionKey() != null ? oReq.getOptionKey() : generateKey(idx))
                        .optionText(oReq.getOptionText())
                        .isCorrect(Boolean.TRUE.equals(oReq.getIsCorrect()))
                        .question(q)
                        .build();
                q.getOptions().add(option);
                idx++;
            }
        }

        Question saved = questionRepository.save(q);
        return mapToDto.QuestionResponsemapToDTO(saved);
    }
    
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question not found");
        }
        questionRepository.deleteById(id);
    }
    
    
    
    private String generateKey(int index) {
        return String.valueOf((char)('A' + index));
    }
    
    
    private void validateRequest(QuestionRequestDTO req) {
        if (req.getQuestionText() == null || req.getQuestionText().isBlank()) {
            throw new RuntimeException("questionText is required");
        }
        if (req.getQuestionType() == null) {
            throw new RuntimeException("questionType is required");
        }
        // Options validation per type
        if (req.getQuestionType() == QuestionType.MCQ) {
            if (req.getOptions() == null || req.getOptions().size() < 2) {
                throw new RuntimeException("MCQ must have at least 2 options");
            }
            long correctCount = req.getOptions().stream().filter(o -> Boolean.TRUE.equals(o.getIsCorrect())).count();
            if (correctCount != 1) throw new RuntimeException("MCQ must have exactly 1 correct option");
        } else if (req.getQuestionType() == QuestionType.MULTI_SELECT) {
            if (req.getOptions() == null || req.getOptions().size() < 2) {
                throw new RuntimeException("MULTI_SELECT must have at least 2 options");
            }
            long correctCount = req.getOptions().stream().filter(o -> Boolean.TRUE.equals(o.getIsCorrect())).count();
            if (correctCount < 1) throw new RuntimeException("MULTI_SELECT must have at least 1 correct option");
        } else if (req.getQuestionType() == QuestionType.TRUE_FALSE) {
            if (req.getOptions() == null || req.getOptions().size() != 2) {
                throw new RuntimeException("TRUE_FALSE must have exactly 2 options (True/False)");
            }
            // ensure one correct option
            long correctCount = req.getOptions().stream().filter(o -> Boolean.TRUE.equals(o.getIsCorrect())).count();
            if (correctCount != 1) throw new RuntimeException("TRUE_FALSE must have exactly 1 correct option");
        }
    }
    
    

}
