package com.rk.edu.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.edu.dto.request.MockTestRequestDTO;
import com.rk.edu.dto.response.MockTestResponseDTO;
import com.rk.edu.services.admin.AdminMockTestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rk/admin")
@RequiredArgsConstructor
public class AdminMockTestController {
	
	private final AdminMockTestService adminMockTestService;
	
	@PostMapping("/testseries/{testSeriesId}/mocktests")
	public ResponseEntity<MockTestResponseDTO> createMockTest(
	        @PathVariable Long testSeriesId,
	        @RequestBody MockTestRequestDTO dto) {

	    return ResponseEntity.ok(adminMockTestService.createMockTest(testSeriesId, dto));
	}

}


