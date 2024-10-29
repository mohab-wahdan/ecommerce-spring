package com.example.ecommerce.thirdParty;

import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    OAouthService loginService;
    @Autowired
    public LoginController(OAouthService loginService) {
        this.loginService = loginService;
    }


    @GetMapping("/grantcode")
    public ResponseEntity<Map<String, Object>> grantCode(@RequestParam("code") String code,
                                                         @RequestParam("scope") String scope,
                                                         @RequestParam("authuser") String authUser,
                                                         @RequestParam("prompt") String prompt) {
        String userProfileJson = loginService.processGrantCode(code);
        Map<String, Object> userProfileMap = new Gson().fromJson(userProfileJson, Map.class);
        return ResponseEntity.ok(userProfileMap);
    }



}
