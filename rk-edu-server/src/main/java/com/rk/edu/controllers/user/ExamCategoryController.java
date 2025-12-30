package com.rk.edu.controllers.user;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.rk.edu.dto.response.ExamCategoryResponseDTO;
import com.rk.edu.model.ExamCategory;
import com.rk.edu.services.user.ExamCategoryService;

@RestController
@RequestMapping("/rk/user/exam-category")
@RequiredArgsConstructor
@CrossOrigin
public class ExamCategoryController {

    private final ExamCategoryService examCategoryService;

    @PostMapping
    public ExamCategory createCategory(@RequestBody ExamCategory category) {
        return examCategoryService.createCategory(category);
    }

    @GetMapping
    public List<ExamCategoryResponseDTO> getAllCategories() {
        return examCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ExamCategoryResponseDTO getCategoryById(@PathVariable Long id) {
        return examCategoryService.getCategoryById(id);
    }

//    @DeleteMapping("/{id}")
//    public void deleteCategory(@PathVariable Long id) {
//        examCategoryService.deleteCategory(id);
//    }
}
