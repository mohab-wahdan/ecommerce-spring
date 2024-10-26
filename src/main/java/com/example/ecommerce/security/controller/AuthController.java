package com.example.ecommerce.security.controller;

import com.example.ecommerce.models.Account;
import com.example.ecommerce.security.UserPrinciple;
import com.example.ecommerce.security.service.JwtService;
import com.example.ecommerce.security.dto.LoginRequest;
import com.example.ecommerce.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> getLoginInfo(@RequestBody LoginRequest loginRequest) {

        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        Map<String,String> map=new HashMap<>();
        if(authentication.isAuthenticated()) {
            map.put("username",loginRequest.getUsername());
            UserPrinciple userPrinciple=(UserPrinciple) authentication.getPrincipal();
            map.put("role",userPrinciple.getAccount().getRoles());
            map.put("jwt-token",jwtService.generateToken(loginRequest.getUsername()));
            map.values().forEach(System.out::println);
           return ResponseEntity.ok(map);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
