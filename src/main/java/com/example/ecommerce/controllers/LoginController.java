package com.example.ecommerce.controllers;


import com.example.ecommerce.dtos.CustomerDTO;
import com.example.ecommerce.dtos.LoginRequest;
import com.example.ecommerce.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = loginService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/getSessionStatus")
    public ResponseEntity<CustomerDTO> getSessionStatus(@RequestParam String username) {
        CustomerDTO customerDTO = loginService.getCustomerByUsername(username);
        if (customerDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(customerDTO);
    }




}
