package com.rk.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.edu.model.InstructionSymbol;

@Repository
public interface InstructionSymbolRepository extends JpaRepository<InstructionSymbol, Long> {}
