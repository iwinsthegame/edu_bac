package com.rk.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.edu.model.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {}
