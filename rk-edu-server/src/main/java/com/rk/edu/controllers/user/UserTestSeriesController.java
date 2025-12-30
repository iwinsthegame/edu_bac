package com.rk.edu.controllers.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.edu.dto.response.TestSeriesResponseDTO;
import com.rk.edu.services.user.UserTestSeriesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rk/user/testseries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserTestSeriesController {

    private final UserTestSeriesService userTestSeriesService;

    
    @GetMapping("/{testseriesId}")
    public ResponseEntity<TestSeriesResponseDTO> getTestSeries(@PathVariable Long testseriesId) {
        return ResponseEntity.ok(userTestSeriesService.getTestSeries(testseriesId));
    }
    
    @GetMapping
    public ResponseEntity<List<TestSeriesResponseDTO>> getAllTestSeries() {
        return ResponseEntity.ok(userTestSeriesService.getAllTestSeries());
    }

}
