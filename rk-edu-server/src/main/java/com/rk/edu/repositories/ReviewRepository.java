package com.rk.edu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rk.edu.enums.EntityType;
import com.rk.edu.model.Discussion;
import com.rk.edu.model.Review;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByEntityTypeAndEntityId(EntityType type, Long id);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.entityType=?1 AND r.entityId=?2")
    Double averageRating(EntityType type, Long id);
}

