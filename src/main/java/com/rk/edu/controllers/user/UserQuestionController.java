package com.rk.edu.controllers.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.edu.dto.response.QuestionResponseDTO;
import com.rk.edu.services.user.UserQuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rk/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserQuestionController {
	
	private final UserQuestionService userQuestionService;

	@GetMapping("/questions/{questionId}")
    public ResponseEntity<QuestionResponseDTO> getById(@PathVariable Long questionId) {
        return ResponseEntity.ok(userQuestionService.getQuestion(questionId));
    }

    @GetMapping("/mocktest/{mockTestId}/questions")
    public ResponseEntity<List<QuestionResponseDTO>> getAllByMockTest(@PathVariable Long mockTestId) {
        return ResponseEntity.ok(userQuestionService.getQuestionsByMockTest(mockTestId));
    }
    
    
    
    
}
