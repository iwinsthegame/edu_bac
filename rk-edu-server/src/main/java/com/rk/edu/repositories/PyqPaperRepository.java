package com.rk.edu.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.edu.model.PyqPaper;

import java.util.List;

public interface PyqPaperRepository extends JpaRepository<PyqPaper, Long> {

	List<PyqPaper> findByExamCategory_Id(Long categoryId);

    List<PyqPaper> findByExamSubCategory_Id(Long subCategoryId);

    List<PyqPaper> findByYear(Integer year);

    List<PyqPaper> findByExamCategory_IdAndYear(Long categoryId, Integer year);
}
