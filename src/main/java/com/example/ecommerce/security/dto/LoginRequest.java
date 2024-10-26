package com.example.ecommerce.security.dto;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
}
