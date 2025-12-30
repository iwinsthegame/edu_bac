package com.rk.edu.services.admin;



import com.rk.edu.dto.request.TestSeriesRequestDTO;
import com.rk.edu.dto.response.MockTestResponseDTO;
import com.rk.edu.dto.response.TestSeriesResponseDTO;
import com.rk.edu.model.ExamSubCategory;
import com.rk.edu.model.MockTest;
import com.rk.edu.model.TestSeries;
import com.rk.edu.repositories.ExamSubCategoryRepository;
import com.rk.edu.repositories.TestSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminTestSeriesService {

    private final TestSeriesRepository testSeriesRepository;
    private final ExamSubCategoryRepository examSubCategoryRepository;
    // Create TestSeries
    public TestSeriesResponseDTO createTestSeries(TestSeriesRequestDTO requestDTO, Long adminId) {
    	
    	System.out.println("createTestSeries......"+requestDTO);
    	ExamSubCategory subCategory = examSubCategoryRepository.findById(
    			requestDTO.getExamSubCategoryId()
        ).orElseThrow(() ->
                new RuntimeException("Exam SubCategory not found with id: "
                        + requestDTO.getExamSubCategoryId()));
    	

        if (testSeriesRepository.existsByTitle(requestDTO.getTitle())) {
        	throw new RuntimeException("Test series with this title already exists");
        }
        
        

        
        TestSeries testSeries = TestSeries.builder()
                .title(requestDTO.getTitle())
                .examSubCategory(subCategory)
//                .examCategory(requestDTO.getExamCategory())
//                .examSubCategory(requestDTO.getExamSubCategory())
                .createdByAdminId(adminId)
                .build();

        if (requestDTO.getMockTests() != null) {

            List<MockTest> mockTests = requestDTO.getMockTests().stream()
                    .map(dto -> {
                        MockTest mock = MockTest.builder()
                                .title(dto.getTitle())
                                .durationMinutes(dto.getDurationMinutes())
                                .isActive(dto.getIsActive())
                                .totalQuestions(dto.getTotalQuestions())
                                .testSeries(testSeries)
                                .build();
                        return mock;
                    })
                    .collect(Collectors.toList());

            testSeries.setMockTests(mockTests);
        }


        TestSeries saved = testSeriesRepository.save(testSeries);

        TestSeriesResponseDTO responseDTO = new TestSeriesResponseDTO();
        responseDTO.setId(saved.getId());
        responseDTO.setTitle(saved.getTitle());
        responseDTO.setExamSubCategory(testSeries.getExamSubCategory().getName());
        responseDTO.setExamCategory(testSeries.getExamSubCategory().getExamCategory().getName());
//        responseDTO.setExamCategory(saved.getExamCategory());
//        responseDTO.setExamSubCategory(saved.getExamSubCategory());
        responseDTO.setCreatedByAdminId(saved.getCreatedByAdminId());

        
        if (saved.getMockTests() != null) {
            List<MockTestResponseDTO> mockTestDTOS =
                    saved.getMockTests().stream()
                            .map(mock -> {
                                MockTestResponseDTO dto = new MockTestResponseDTO();
                                dto.setId(mock.getId());
                                dto.setTitle(mock.getTitle());
                                dto.setDurationMinutes(mock.getDurationMinutes());
                                dto.setTotalQuestions(mock.getTotalQuestions());
                                dto.setIsActive(mock.getIsActive());
                                return dto;
                            })
                            .collect(Collectors.toList());

            responseDTO.setMockTests(mockTestDTOS);
        }


        return responseDTO;
    }
}
