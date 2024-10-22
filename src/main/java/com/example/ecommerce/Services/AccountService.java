//package com.example.ecommerce.Services;
//
//import com.example.ecommerce.models.Account;
//import com.example.ecommerce.models.Customer;
//import com.example.ecommerce.repositories.AccountRepository;
//import com.example.ecommerce.repositories.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AccountService {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    public boolean login(String username, String password) {
//        Optional<Customer> customer = customerRepository.findAccountByUserName(username);
//
//        return customer.isPresent() && customer.get().getPassword().equals(password);
//    }
//}
