package com.rk.edu.dto.response;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String fullName;
    private String email;
    private String mobile;
    private String profileImage;
    private String role;
    private String state;
    private String city;
}
