package com.rk.edu.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.edu.model.AttemptQuestion;

@Repository
public interface AttemptQuestionRepository extends JpaRepository<AttemptQuestion, Long> {
    List<AttemptQuestion> findByAttemptId(Long attemptId);
    Optional<AttemptQuestion> findByAttemptIdAndQuestionId(Long attemptId, Long questionId);
}

