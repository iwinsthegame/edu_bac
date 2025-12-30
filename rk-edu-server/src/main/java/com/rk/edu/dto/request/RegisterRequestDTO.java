package com.rk.edu.dto.request;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String fullName;
    private String email;
    private String password;
    private String mobile;
    private String profileImage;
    private String state;
    private String city;
    private String role;
}