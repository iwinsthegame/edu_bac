package com.rk.edu.controllers.admin;



import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rk.edu.dto.request.TestSeriesRequestDTO;
import com.rk.edu.dto.response.TestSeriesResponseDTO;
import com.rk.edu.services.admin.AdminTestSeriesService;

@RestController
@RequestMapping("/rk/admin")
@RequiredArgsConstructor
public class AdminTestSeriesController {

    private final AdminTestSeriesService admintestSeriesService;

    @PostMapping ("/testseries")
    public ResponseEntity<TestSeriesResponseDTO> createTestSeries(
            @RequestBody TestSeriesRequestDTO requestDTO,
            @RequestParam Long adminId
    ) {
        TestSeriesResponseDTO responseDTO = admintestSeriesService.createTestSeries(requestDTO, adminId);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
