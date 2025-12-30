package com.rk.edu.exception;

import lombok.*;

@Data
@AllArgsConstructor
public class ApiException {
    private String message;
    private int status;
}

