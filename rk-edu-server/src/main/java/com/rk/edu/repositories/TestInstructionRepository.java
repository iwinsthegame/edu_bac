package com.rk.edu.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.edu.model.MockTestInstruction;

@Repository
public interface TestInstructionRepository extends JpaRepository<MockTestInstruction, Long> {
    Optional<MockTestInstruction> findByMockTestId(Long mockId);
}
