package com.rk.edu.controllers.user;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rk.edu.enums.EntityType;
import com.rk.edu.model.Discussion;
import com.rk.edu.model.Review;
import com.rk.edu.services.user.DiscussionService;
import com.rk.edu.services.user.ReviewService;




@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

//    @Autowired
//    private ReviewService service;
//
//    @PostMapping
////    @PreAuthorize("isAuthenticated()")
//    public Review add(@RequestBody Review r, Principal p) {
//        r.setUserId(Long.parseLong(p.getName()));
//        return service.add(r);
//    }
//
//    @GetMapping
//    public List<Review> list(
//        @RequestParam EntityType entityType,
//        @RequestParam Long entityId
//    ) {
//        return service.list(entityType, entityId);
//    }
//
//    @GetMapping("/average")
//    public double avg(
//        @RequestParam EntityType entityType,
//        @RequestParam Long entityId
//    ) {
//        return service.average(entityType, entityId);
//    }
}

