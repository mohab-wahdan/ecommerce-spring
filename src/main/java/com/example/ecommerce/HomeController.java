package com.example.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
public class HomeController {
    @RequestMapping("/")
    public ResponseEntity<Void> redirectToIndex() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/index.jsp"))
                .build();
    }
    @GetMapping("/login.jsp")
    public String showLoginPage() {
        return "login"; // This will resolve to /WEB-INF/views/login.jsp
    }
}
