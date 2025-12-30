package com.rk.edu.services.user;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.rk.edu.dto.response.ExamSubCategoryResponseDTO;
import com.rk.edu.dto.response.TestSeriesResponseDTO;
import com.rk.edu.model.ExamCategory;
import com.rk.edu.model.ExamSubCategory;
import com.rk.edu.repositories.ExamCategoryRepository;
import com.rk.edu.repositories.ExamSubCategoryRepository;

@Service
@RequiredArgsConstructor
public class ExamSubCategoryService {

    private final ExamSubCategoryRepository subCategoryRepository;
    private final ExamCategoryRepository categoryRepository;

    
    public ExamSubCategory createSubCategory(Long categoryId, ExamSubCategory subCategory) {

        ExamCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        subCategory.setExamCategory(category);
        
        return subCategoryRepository.save(subCategory);
    }

    
    public List<ExamSubCategoryResponseDTO> getSubCategoriesByCategory(Long categoryId) {

        List<ExamSubCategory> subCategories =
        		subCategoryRepository.findByExamCategoryId(categoryId);

        return subCategories.stream().map(sub -> {

            ExamSubCategoryResponseDTO dto = new ExamSubCategoryResponseDTO();
            dto.setId(sub.getId());
            dto.setName(sub.getName());
            dto.setDescription(sub.getDescription());

            // Map Test Series (lightweight)
            if (sub.getTestSeriesList() != null) {
                List<TestSeriesResponseDTO> testSeriesDTOs = sub.getTestSeriesList()
                        .stream()
                        .map(series -> {
                            TestSeriesResponseDTO tsDto = new TestSeriesResponseDTO();
                            tsDto.setId(series.getId());
                            tsDto.setTitle(series.getTitle());
                            tsDto.setCreatedByAdminId(series.getCreatedByAdminId());
                           
                            if (series.getExamSubCategory() != null) {
                                tsDto.setExamSubCategory(series.getExamSubCategory().getName());
                            }

                            tsDto.setMockTests(null); // avoid heavy payload
                            return tsDto;
                        })
                        .collect(Collectors.toList());

                dto.setTestSeriesList(testSeriesDTOs);
            }

            return dto;
        }).collect(Collectors.toList());
    }

}

