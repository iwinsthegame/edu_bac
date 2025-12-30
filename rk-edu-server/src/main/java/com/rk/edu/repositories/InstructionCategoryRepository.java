package com.rk.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.edu.model.InstructionCategory;

@Repository
public interface InstructionCategoryRepository extends JpaRepository<InstructionCategory, Long> {}
