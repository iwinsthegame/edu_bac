package com.rk.edu.services.user;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.rk.edu.dto.response.ExamCategoryResponseDTO;
import com.rk.edu.dto.response.ExamSubCategoryResponseDTO;
import com.rk.edu.model.ExamCategory;
import com.rk.edu.repositories.ExamCategoryRepository;

@Service
@RequiredArgsConstructor
public class ExamCategoryService {

    private final ExamCategoryRepository examCategoryRepository;

    
    public ExamCategory createCategory(ExamCategory category) {
        return examCategoryRepository.save(category);
    }

    
    public List<ExamCategoryResponseDTO> getAllCategories() {

        List<ExamCategory> examCategories = examCategoryRepository.findAll();

        return examCategories.stream().map(category -> {

            ExamCategoryResponseDTO dto = new ExamCategoryResponseDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setDescription(category.getDescription());
            dto.setLogoUrl(category.getLogoUrl());

            // Map subcategories
            if (category.getSubCategories() != null) {

                List<ExamSubCategoryResponseDTO> subDtos =
                        category.getSubCategories().stream().map(sub -> {

                            ExamSubCategoryResponseDTO subDto =
                                    new ExamSubCategoryResponseDTO();

                            subDto.setId(sub.getId());
                            subDto.setName(sub.getName());
                            subDto.setDescription(sub.getDescription());

                            // DO NOT set testSeriesList now
                            subDto.setTestSeriesList(null);

                            return subDto;

                        }).toList();

                dto.setSubCategories(subDtos);
            }

            return dto;

        }).toList();
    }


    
    public ExamCategoryResponseDTO getCategoryById(Long id) {

        ExamCategory category = examCategoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Exam Category not found with id: " + id)
                );

        ExamCategoryResponseDTO dto = new ExamCategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setLogoUrl(category.getLogoUrl());

        // Map subcategories properly
        if (category.getSubCategories() != null) {

            List<ExamSubCategoryResponseDTO> subDtos =
                    category.getSubCategories().stream().map(sub -> {

                        ExamSubCategoryResponseDTO subDto =
                                new ExamSubCategoryResponseDTO();

                        subDto.setId(sub.getId());
                        subDto.setName(sub.getName());
                        subDto.setDescription(sub.getDescription());

                        // Not loading test series here
                        subDto.setTestSeriesList(null);

                        return subDto;

                    }).toList();

            dto.setSubCategories(subDtos);
        }

        return dto;
    }

}
