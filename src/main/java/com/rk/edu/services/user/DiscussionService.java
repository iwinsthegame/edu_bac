package com.rk.edu.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.edu.enums.EntityType;
import com.rk.edu.model.Discussion;
import com.rk.edu.repositories.DiscussionRepository;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository repo;

    public Discussion add(Discussion d) {
        return repo.save(d);
    }

    public List<Discussion> list(EntityType type, Long id) {
        return repo.findByEntityTypeAndEntityIdOrderByCreatedAtDesc(type, id);
    }

    public void upvote(Long id) {
        Discussion d = repo.findById(id).orElseThrow();
        d.setUpvotes(d.getUpvotes() + 1);
        repo.save(d);
    }
}

