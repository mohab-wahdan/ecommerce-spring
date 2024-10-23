package com.example.ecommerce.Services;

import com.example.ecommerce.dtos.LoginDTO;
import com.example.ecommerce.models.Account;

import org.springframework.stereotype.Service;

@Service
public class LoginService {


    public boolean validateUser(LoginDTO loginDTO) {

        String userName = loginDTO.getUserName();
        String password = loginDTO.getPassword();
        // Replace with actual database retrieval logic
        Account mockAccount = new Account(userName, password);

        return mockAccount.getUserName().equals(loginDTO.getUserName()) &&
                mockAccount.getPassword().equals(loginDTO.getPassword());
    }
}

