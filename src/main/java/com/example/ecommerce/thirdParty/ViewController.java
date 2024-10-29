package com.example.ecommerce.thirdParty;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home"); // Assumes home.html is in src/main/resources/templates
    }
}