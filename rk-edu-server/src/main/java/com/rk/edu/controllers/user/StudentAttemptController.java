package com.rk.edu.controllers.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rk.edu.dto.request.AttemptResultDTO;
import com.rk.edu.dto.request.LeaderboardEntryDTO;
import com.rk.edu.dto.request.QuestionSolutionDTO;
import com.rk.edu.dto.request.SaveAnswerRequestDTO;
import com.rk.edu.dto.request.SubjectAnalyticsDTO;
import com.rk.edu.dto.response.StartAttemptResponseDTO;
import com.rk.edu.dto.response.SubmitAttemptResponseDTO;
import com.rk.edu.repositories.UserRepository;
import com.rk.edu.services.user.StudentAttemptService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rk/user/attempt")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentAttemptController {
	
	private final StudentAttemptService attemptService;
    private final UserRepository userRepository;
    
    @PostMapping("/start/{mockTestId}/{studentId}")
    public ResponseEntity<StartAttemptResponseDTO> startAttempt(
            @PathVariable Long mockTestId,
            @PathVariable Long studentId) { // replace with principal in real app
        return ResponseEntity.ok(attemptService.startAttempt(mockTestId, studentId));
    }
    
    @PostMapping("/{attemptId}/save-answer/{studentId}")
    public ResponseEntity<String> saveAnswer(
            @PathVariable Long attemptId,
            @PathVariable Long studentId,
            @RequestBody SaveAnswerRequestDTO req) {
        attemptService.saveAnswer(attemptId, studentId, req);
        return ResponseEntity.ok("Saved Answer");
    }
    
    @PostMapping("/{attemptId}/submit/{studentId}")
    public ResponseEntity<SubmitAttemptResponseDTO> submitAttempt(
            @PathVariable Long attemptId,
            @PathVariable Long studentId) {
        return ResponseEntity.ok(attemptService.submitAttempt(attemptId, studentId));
    }
    
    
    @GetMapping("/{attemptId}/result/{studentId}")
    public ResponseEntity<AttemptResultDTO> getResult(
            @PathVariable Long attemptId,
            @PathVariable Long studentId) {
        return ResponseEntity.ok(attemptService.getResult(attemptId, studentId));
    }
    
    @GetMapping("/leaderboard/{mockTestId}/{limit}")
    public ResponseEntity<List<LeaderboardEntryDTO>> leaderboard(
            @PathVariable Long mockTestId,
            @PathVariable int limit) {
    	System.out.println("leaderboard : "+"mockTestId : "+mockTestId);
        return ResponseEntity.ok(attemptService.getLeaderboard(mockTestId, limit));
    }

    @GetMapping("/{attemptId}/subject-analytics")
    public ResponseEntity<List<SubjectAnalyticsDTO>> subjectAnalytics(
            @PathVariable Long attemptId,
            @RequestParam Long studentId) {
        return ResponseEntity.ok(attemptService.getSubjectAnalytics(attemptId, studentId));
    }

    @GetMapping("/{attemptId}/solutions")
    public ResponseEntity<List<QuestionSolutionDTO>> solutions(
            @PathVariable Long attemptId,
            @RequestParam Long studentId) {
        return ResponseEntity.ok(attemptService.getQuestionWiseSolutions(attemptId, studentId));
    }
}

