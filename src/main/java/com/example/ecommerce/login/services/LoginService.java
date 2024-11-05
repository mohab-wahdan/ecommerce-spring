package com.example.ecommerce.login.services;

import com.example.ecommerce.cutomer.dto.CustomerDTO;
import com.example.ecommerce.cutomer.mappers.CustomerMapper;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.cutomer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public CustomerDTO getCustomerByUsername(String username) {
        Customer customer = customerRepository.findByAccountUserName(username);
        if (customer == null) {
            logger.warn("Customer not found for username: {}", username);
            return null;
        }
        return customerMapper.toDTO(customer);
    }





}
