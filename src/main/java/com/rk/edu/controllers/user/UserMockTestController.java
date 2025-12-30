package com.rk.edu.controllers.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.edu.dto.response.MockTestResponseDTO;
import com.rk.edu.services.user.UserMockTestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rk/user/mocktest/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserMockTestController {
	
	private final UserMockTestService userMockTestService;
	
	@GetMapping("{mocktestId}")
	public ResponseEntity<MockTestResponseDTO> getMockTest(@PathVariable Long mocktestId) {
	    return ResponseEntity.ok(userMockTestService.getMockTest(mocktestId));
	}
	
	
	@GetMapping("testseries/{testseriesId}")
	public ResponseEntity<List<MockTestResponseDTO>> getMockTestsBySeries(@PathVariable Long testseriesId) {
	    return ResponseEntity.ok(userMockTestService.getMockTestsBySeries(testseriesId));
	}


}
