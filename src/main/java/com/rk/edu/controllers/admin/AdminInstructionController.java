package com.rk.edu.controllers.admin;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.edu.dto.request.CreateInstructionRequestDTO;
import com.rk.edu.dto.response.TestInstructionResponseDTO;
import com.rk.edu.services.user.InstructionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rk/admin/instructions")
@RequiredArgsConstructor
public class AdminInstructionController {

    private final InstructionService instructionService;

    @PostMapping("/create")
    public ResponseEntity<?> createInstruction(@RequestBody CreateInstructionRequestDTO dto) {
        return ResponseEntity.ok(instructionService.createInstruction(dto));
    }

    @GetMapping("/mocktest/{mockTestId}")
    public ResponseEntity<TestInstructionResponseDTO> getInstruction(@PathVariable Long mockTestId) {
        return ResponseEntity.ok(instructionService.getInstruction(mockTestId));
    }
}

