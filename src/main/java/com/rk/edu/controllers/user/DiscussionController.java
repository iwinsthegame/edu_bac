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
import com.rk.edu.services.user.DiscussionService;

@RestController
@RequestMapping("/api/discussion")
//@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "*")
public class DiscussionController {

    @Autowired
    private DiscussionService service;

    @PostMapping
    public Discussion post(@RequestBody Discussion d, Principal p) {
//        d.setUserId(Long.parseLong(p.getName()));
    	 d.setUserId(5L);
        return service.add(d);
    }

    @GetMapping
    public List<Discussion> list(
        @RequestParam EntityType entityType,
        @RequestParam Long entityId
    ) {
        return service.list(entityType, entityId);
    }

    @PostMapping("/{id}/upvote")
    public void upvote(@PathVariable Long id) {
        service.upvote(id);
    }
}
