package com.rk.edu.services.user;

import java.util.Optional;


import org.springframework.stereotype.Service;

import com.rk.edu.dto.request.LoginRequestDTO;
import com.rk.edu.dto.request.RegisterRequestDTO;
import com.rk.edu.dto.response.UserResponseDTO;
import com.rk.edu.enums.Role;
import com.rk.edu.model.User;
import com.rk.edu.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ---------------- REGISTER ----------------
    public UserResponseDTO register(RegisterRequestDTO req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(req.getPassword())
                //.password(passwordEncoder.encode(req.getPassword()))
                .mobile(req.getMobile())
                .profileImage(req.getProfileImage())
                .role(Role.valueOf(req.getRole().toUpperCase()))
                .state(req.getState())
                .city(req.getCity())
                .build();

        userRepository.save(user);
        return toDTO(user);
    }

    // ---------------- LOGIN ----------------
    public UserResponseDTO login(LoginRequestDTO req) {

        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

//        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid email or password");
//        }

        return toDTO(user);
    }

    // ---------------- GET USER BY ID ----------------
    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toDTO(user);
    }

    // Convert entity to DTO
    private UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setMobile(user.getMobile());
        dto.setProfileImage(user.getProfileImage());
        dto.setRole(user.getRole().name());
        dto.setState(user.getState());
        dto.setCity(user.getCity());
        return dto;
    }
}
