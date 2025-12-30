package com.rk.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.edu.model.InstructionPoint;
@Repository
public interface InstructionPointRepository extends JpaRepository<InstructionPoint, Long> {}

