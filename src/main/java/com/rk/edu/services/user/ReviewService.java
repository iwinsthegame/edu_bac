package com.rk.edu.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.edu.enums.EntityType;
import com.rk.edu.model.Discussion;
import com.rk.edu.model.Review;
import com.rk.edu.repositories.DiscussionRepository;
import com.rk.edu.repositories.ReviewRepository;


@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repo;

    public Review add(Review r) {
        return repo.save(r);
    }

    public List<Review> list(EntityType type, Long id) {
        return repo.findByEntityTypeAndEntityId(type, id);
    }

    public double average(EntityType type, Long id) {
        return repo.averageRating(type, id) == null ? 0 : repo.averageRating(type, id);
    }
}

