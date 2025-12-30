package com.rk.edu.services.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rk.edu.dto.response.MockTestResponseDTO;
import com.rk.edu.helper.MapToDto;
import com.rk.edu.model.MockTest;
import com.rk.edu.model.TestSeries;
import com.rk.edu.repositories.MockTestRepository;
import com.rk.edu.repositories.TestSeriesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMockTestService {
	
	private final TestSeriesRepository testSeriesRepository;
	private final MockTestRepository mockTestRepository;
	private final MapToDto mapToDto;
	
	public MockTestResponseDTO getMockTest(Long id) {
	    MockTest mock = mockTestRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Mock test not found"));

	    return mapToDto.MocktestResponseMapToDTO(mock);
	}
	
	public List<MockTestResponseDTO> getMockTestsBySeries(Long tsId) {

	    TestSeries ts = testSeriesRepository.findById(tsId)
	            .orElseThrow(() -> new RuntimeException("Test Series not found"));

	    return ts.getMockTests().stream()
	            .map(mapToDto::MocktestResponseMapToDTO)
	            .collect(Collectors.toList());
	}


}
