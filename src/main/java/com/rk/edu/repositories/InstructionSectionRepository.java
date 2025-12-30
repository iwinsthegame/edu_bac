package com.rk.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.edu.model.InstructionSection;

@Repository
public interface InstructionSectionRepository extends JpaRepository<InstructionSection, Long> {}

