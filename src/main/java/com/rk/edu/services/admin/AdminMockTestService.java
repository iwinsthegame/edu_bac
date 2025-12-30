package com.rk.edu.services.admin;

import com.rk.edu.dto.request.MockTestRequestDTO;
import com.rk.edu.dto.request.TestSeriesRequestDTO;
import com.rk.edu.dto.response.MockTestResponseDTO;
import com.rk.edu.dto.response.TestSeriesResponseDTO;
import com.rk.edu.model.MockTest;
import com.rk.edu.model.TestSeries;
import com.rk.edu.repositories.MockTestRepository;
import com.rk.edu.repositories.TestSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.rk.edu.helper.MapToDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminMockTestService {
	
	private final TestSeriesRepository testSeriesRepository;
	private final MockTestRepository mockTestRepository;
	private final MapToDto mapToDto;
	
	public MockTestResponseDTO createMockTest(Long testSeriesId, MockTestRequestDTO dto) {

	    TestSeries ts = testSeriesRepository.findById(testSeriesId)
	            .orElseThrow(() -> new RuntimeException("Test Series not found"));

	    MockTest mock = MockTest.builder()
	            .title(dto.getTitle())
	            .durationMinutes(dto.getDurationMinutes())
	            .totalQuestions(dto.getTotalQuestions())
	            .totalMarks(dto.getTotalMarks())
	            .isActive(dto.getIsActive())
	            .testSeries(ts)
	            .build();

	    MockTest savedMock = mockTestRepository.save(mock);
	    
	    return mapToDto.MocktestResponseMapToDTO(savedMock);

	    
	}

	


}
