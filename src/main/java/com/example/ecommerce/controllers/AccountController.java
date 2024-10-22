//package com.example.ecommerce.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collections;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/account")
//public class AccountController {
//
//    @Autowired
//    private AccountService  accountService;
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Boolean>> login(@RequestBody Map<String, String> request) {
//        String username = request.get("username");
//        String password = request.get("password");
//
//        boolean success = accountService.login(username, password);
//        Map<String, Boolean> response = Collections.singletonMap("success", success);
//        return ResponseEntity.ok(response);
//    }
//}
//
