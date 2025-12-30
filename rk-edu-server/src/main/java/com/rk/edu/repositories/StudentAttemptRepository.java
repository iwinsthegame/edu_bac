package com.rk.edu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.edu.enums.AttemptStatus;
import com.rk.edu.model.StudentAttempt;

@Repository
public interface StudentAttemptRepository extends JpaRepository<StudentAttempt, Long> {
    List<StudentAttempt> findByStudentId(Long studentId);
    List<StudentAttempt> findByMockTestIdAndStatus(Long mockTestId, AttemptStatus status);
    List<StudentAttempt> findByMockTestIdOrderByTotalMarksDesc(Long mockTestId);
}

