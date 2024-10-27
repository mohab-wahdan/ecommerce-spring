package com.example.ecommerce.thirdParty;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/google-login")
public class MainController {

    @GetMapping
    public String home() {
        return "home page";
    }



}
