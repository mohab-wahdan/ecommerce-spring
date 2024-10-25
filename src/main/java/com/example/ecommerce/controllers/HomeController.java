package com.example.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/login.jsp")
    public String showLoginPage() {
        return "login"; // This will resolve to /WEB-INF/views/login.jsp
    }
}
