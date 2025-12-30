package com.rk.edu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.edu.enums.EntityType;
import com.rk.edu.model.Discussion;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    List<Discussion> findByEntityTypeAndEntityIdOrderByCreatedAtDesc(
        EntityType type, Long id);
}

