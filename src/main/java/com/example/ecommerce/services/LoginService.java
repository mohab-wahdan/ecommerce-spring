package com.example.ecommerce.services;

import com.example.ecommerce.dtos.CustomerDTO;
import com.example.ecommerce.mappers.CustomerMapper;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper ;

    @Autowired
    public LoginService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public boolean authenticate(String username, String password) {
        Customer customer = customerRepository.findByAccountUserName(username);
        if (customer != null && customer.getAccount().getPassword().equals(password)) {
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }


    public CustomerDTO getCustomerByUsername(String username) {
        Customer customer = customerRepository.findByAccountUserName(username);
        if (customer == null) {
            // Log a message to indicate that the customer was not found
            System.out.println("Customer not found for username: " + username);
            return null;
        }
        return customerMapper.toDTO(customer);
    }


}
