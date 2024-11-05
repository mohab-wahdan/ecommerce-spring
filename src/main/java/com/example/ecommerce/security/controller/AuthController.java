package com.example.ecommerce.security.controller;

import com.example.ecommerce.cutomer.dto.CustomerDTO;
import com.example.ecommerce.security.UserPrinciple;
import com.example.ecommerce.security.service.JwtService;
import com.example.ecommerce.security.dto.LoginRequest;
import com.example.ecommerce.security.service.UserService;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/customer/{username}")
    public ResponseEntity<CustomerDTO> getCustomerByUsername(@PathVariable String username) {
        CustomerDTO customerDTO = userDetailsService.getCustomerByUsername(username);
        if (customerDTO != null) {
            return ResponseEntity.ok(customerDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/profile/google")
    public JsonObject getProfileDetailsGoogle(@RequestHeader("Authorization") String accessToken) {
        // Remove "Bearer " prefix if it's included in the Authorization header
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        String url = "https://www.googleapis.com/oauth2/v2/userinfo";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        // Parse the response body into a JsonObject
        JsonObject jsonObject = new Gson().fromJson(response.getBody(), JsonObject.class);

        return jsonObject;
    }
}
