package com.example.ecommerce.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotEmpty(message = "Username is required")
    private String userName;

    @NotEmpty(message = "Password is required")
    private String password;
}
