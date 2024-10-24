package com.example.ecommerce.Services;

import com.example.ecommerce.dtos.CustomerDTO;
import com.example.ecommerce.mapper.CustomerMapper;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private CustomerRepository customerRepository;

    public boolean authenticate(String username, String password) {
        Customer customer = customerRepository.findByAccountUserName(username);
        if (customer != null && customer.getAccount().getPassword().equals(password)) {
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }


    public CustomerDTO getCustomerByUsername(String username) {
        Customer customer = customerRepository.findByAccountUserName(username);
        CustomerMapper customerMapper = new CustomerMapper();
        return customerMapper.toDTO(customer);
    }
}
