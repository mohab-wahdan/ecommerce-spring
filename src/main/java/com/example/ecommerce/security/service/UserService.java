package com.example.ecommerce.security.service;

import com.example.ecommerce.models.Admin;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.repositories.AdminRepository;
import com.example.ecommerce.repositories.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements UserDetailsService {
    private CustomerRepository customerRepository;
    private AdminRepository adminRepository;

    public UserService(CustomerRepository customerRepository, AdminRepository adminRepository) {
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer =customerRepository.findByAccount_UserName(username);
        if(customer.isPresent()){
            return customer.map(CustomerPrinciple::new).get();

        }

        Optional<Admin> admin =adminRepository.findByAccount_UserName(username);
        if(admin.isPresent()){

        }

       throw new UsernameNotFoundException("Invalid username or password : " + username);
    }
}
