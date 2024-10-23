package com.example.ecommerce.Services;

import com.example.ecommerce.dtos.LoginRequestDTO;
import com.example.ecommerce.dtos.LoginResponseDTO;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    // Assuming you have a UserRepository to check credentials
    @Autowired
    private CustomerRepository userRepository;
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Optional<Customer> user = userRepository.findByAccount_UserNameAndAccount_Password(username,password);
        if (user.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }

        // Continue with successful login logic
        return new LoginResponseDTO("Login successful", "token_here");
    }

}
