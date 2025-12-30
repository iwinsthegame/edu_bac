package com.rk.edu.controllers.user;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.rk.edu.dto.response.ExamSubCategoryResponseDTO;
import com.rk.edu.model.ExamSubCategory;
import com.rk.edu.services.user.ExamSubCategoryService;

@RestController
@RequestMapping("/rk/user/exam-subcategory")
@RequiredArgsConstructor
@CrossOrigin
public class ExamSubCategoryController {

    private final ExamSubCategoryService examSubCategoryService;

    @PostMapping("/{categoryId}")
    public ExamSubCategory createSubCategory(
            @PathVariable Long categoryId,
            @RequestBody ExamSubCategory subCategory) {

        return examSubCategoryService.createSubCategory(categoryId, subCategory);
    }

    @GetMapping("/category/{categoryId}")
    public List<ExamSubCategoryResponseDTO> getSubCategories(
            @PathVariable Long categoryId) {

        return examSubCategoryService.getSubCategoriesByCategory(categoryId);
    }
}
