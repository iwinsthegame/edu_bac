package com.rk.edu.controllers.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.edu.dto.request.QuestionRequestDTO;
import com.rk.edu.dto.response.QuestionResponseDTO;
import com.rk.edu.services.admin.AdminQuestionService;

import lombok.*;

@RestController
@RequestMapping("/rk/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminQuestionController {
	
	private final AdminQuestionService adminQuestionService;

	@PostMapping("/mocktest/{mockTestId}/questions")
    public ResponseEntity<QuestionResponseDTO> create(
            @PathVariable Long mockTestId,
            @RequestBody QuestionRequestDTO req) {

        return ResponseEntity.ok(adminQuestionService.createQuestion(mockTestId, req));
    }
	

	 @PutMapping("/questions/{id}")
	    public ResponseEntity<QuestionResponseDTO> update(
	            @PathVariable Long id,
	            @RequestBody QuestionRequestDTO req) {

	        return ResponseEntity.ok(adminQuestionService.updateQuestion(id, req));
	    }

	    @DeleteMapping("/questions/{id}")
	    public ResponseEntity<String> delete(@PathVariable Long id) {
	        adminQuestionService.deleteQuestion(id);
	        return ResponseEntity.ok("Question deleted");
	    }

	    @PostMapping("/mocktest/{mockTestId}/questions/bulk")
	    public ResponseEntity<List<QuestionResponseDTO>> createBulk(
	            @PathVariable Long mockTestId,
	            @RequestBody List<QuestionRequestDTO> requests) {

	        return ResponseEntity.ok(
	                adminQuestionService.createQuestions(mockTestId, requests)
	        );
	    }
	    
	    
}
