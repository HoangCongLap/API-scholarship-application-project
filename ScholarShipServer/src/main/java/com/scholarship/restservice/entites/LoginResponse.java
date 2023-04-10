package com.scholarship.restservice.entites;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private  boolean loginSuccess;
    private String username;
}
