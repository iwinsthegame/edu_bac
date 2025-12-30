package com.rk.edu.services.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rk.edu.dto.response.MockTestResponseDTO;
import com.rk.edu.dto.response.TestSeriesResponseDTO;
import com.rk.edu.model.TestSeries;
import com.rk.edu.repositories.TestSeriesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserTestSeriesService {

    private final TestSeriesRepository testSeriesRepository;

    // ------------------- GET Single TestSeries -------------------
    public TestSeriesResponseDTO getTestSeries(Long testSeriesId) {

        TestSeries testSeries = testSeriesRepository.findById(testSeriesId)
                .orElseThrow(() -> new RuntimeException("Test Series not found"));

        TestSeriesResponseDTO responseDTO = new TestSeriesResponseDTO();
        responseDTO.setId(testSeries.getId());
        responseDTO.setTitle(testSeries.getTitle());
        responseDTO.setExamSubCategory(testSeries.getExamSubCategory().getName());
        responseDTO.setExamCategory(testSeries.getExamSubCategory().getExamCategory().getName());
           
        responseDTO.setCreatedByAdminId(testSeries.getCreatedByAdminId());

        if (testSeries.getMockTests() != null) {

            List<MockTestResponseDTO> mockDtos = testSeries.getMockTests()
                    .stream()
                    .map(mock -> {
                        MockTestResponseDTO dto = new MockTestResponseDTO();
                        dto.setId(mock.getId());
                        dto.setTitle(mock.getTitle());
                        dto.setDurationMinutes(mock.getDurationMinutes());
                        dto.setTotalQuestions(mock.getTotalQuestions());
                        dto.setTotalMarks(mock.getTotalMarks());
                        dto.setIsActive(mock.getIsActive());
                        return dto;
                    }).collect(Collectors.toList());

            responseDTO.setMockTests(mockDtos);
        }

        return responseDTO;
    }
    
    public List<TestSeriesResponseDTO> getAllTestSeries() {
        return testSeriesRepository.findAll()
                .stream()
                .map(ts -> getTestSeries(ts.getId())) // reuse existing method
                .collect(Collectors.toList());
    }

}
