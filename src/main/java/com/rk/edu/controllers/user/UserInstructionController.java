package com.rk.edu.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.edu.dto.response.TestInstructionResponseDTO;
import com.rk.edu.services.user.InstructionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rk/user/instructions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserInstructionController {

    private final InstructionService instructionService;



    @GetMapping("/mocktest/{mockTestId}")
    public ResponseEntity<TestInstructionResponseDTO> getInstruction(@PathVariable Long mockTestId) {
    	System.out.println("mockTestId"+mockTestId);
        return ResponseEntity.ok(instructionService.getInstruction(mockTestId));
    }
}

