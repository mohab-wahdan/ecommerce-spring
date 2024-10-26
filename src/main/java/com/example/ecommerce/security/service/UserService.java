package com.example.ecommerce.security.service;

import com.example.ecommerce.models.Account;
import com.example.ecommerce.models.Admin;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.repositories.AdminRepository;
import com.example.ecommerce.repositories.CustomerRepository;
import com.example.ecommerce.security.UserPrinciple;
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
            Account account1 = customer.get().getAccount();
            return new UserPrinciple(account1);

        }

        Optional<Admin> admin =adminRepository.findByAccount_UserName(username);
        if(admin.isPresent()){
            Account account2 = admin.get().getAccount();
            return new UserPrinciple(account2);
        }

       throw new UsernameNotFoundException("Invalid username or password : " + username);
    }
}
