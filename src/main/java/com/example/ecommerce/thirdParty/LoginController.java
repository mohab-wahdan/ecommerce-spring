package com.example.ecommerce.thirdParty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    OAouthService loginService;
    @Autowired
    public LoginController(OAouthService loginService) {
        this.loginService = loginService;
    }


    @GetMapping("/grantcode")
    public ResponseEntity<String> grantCode(@RequestParam("code") String code,
                                            @RequestParam("scope") String scope,
                                            @RequestParam("authuser") String authUser,
                                            @RequestParam("prompt") String prompt) {
        String userProfile = loginService.processGrantCode(code);
        return ResponseEntity.ok(userProfile);  // Return user profile as a JSON response
    }


}
