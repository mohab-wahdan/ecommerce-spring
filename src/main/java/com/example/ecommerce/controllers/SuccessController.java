package com.example.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SuccessController {
    @GetMapping("/paymentSuccess")
    public String home(@RequestParam String amount, Model model) {
        model.addAttribute("amount", amount);
        return "payment-success";
    }
}
