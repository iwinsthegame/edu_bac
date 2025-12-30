package com.rk.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rk.edu.model.ExamCategory;

public interface ExamCategoryRepository extends JpaRepository<ExamCategory, Long> {

    boolean existsByName(String name);
}

