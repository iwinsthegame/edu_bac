package com.rk.edu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rk.edu.model.ExamSubCategory;

public interface ExamSubCategoryRepository extends JpaRepository<ExamSubCategory, Long> {

    List<ExamSubCategory> findByExamCategoryId(Long examCategoryId);
}
