//package com.example.ecommerce.controllers;
//
//
//import com.example.ecommerce.models.Request;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/pay")
//public class AppController {
//    @Value("${stripe.api.publicKey}")
//    private String publicKey;
////    @GetMapping("/")
////    public String home(Model model){
////        model.addAttribute("request", new Request());
////        return "index";
////    }
//
//    @GetMapping("/{amount}")
//    public String showCard(Model model, @PathVariable int amount) {
////        if (bindingResult.hasErrors()){
////            return "index";
////        }
//        model.addAttribute("publicKey", publicKey);
//        model.addAttribute("amount", amount);
//        model.addAttribute("email", "chickly_app@gmail.com");
//        model.addAttribute("productName", "chickly_products");
//        return "checkout";
//    }
//}
