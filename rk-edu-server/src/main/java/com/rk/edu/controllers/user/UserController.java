package com.rk.edu.controllers.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rk.edu.dto.request.LoginRequestDTO;
import com.rk.edu.dto.request.RegisterRequestDTO;
import com.rk.edu.dto.response.UserResponseDTO;
import com.rk.edu.services.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rk/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO req) {
        return ResponseEntity.ok(userService.register(req));
    }

    
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO req) {
        return ResponseEntity.ok(userService.login(req));
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }
}
