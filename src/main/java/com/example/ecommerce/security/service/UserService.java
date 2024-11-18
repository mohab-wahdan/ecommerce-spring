package com.example.ecommerce.security.service;

import com.example.ecommerce.dtos.CustomerDTO;
import com.example.ecommerce.mappers.CustomerMapper;
import com.example.ecommerce.models.Account;
import com.example.ecommerce.models.Admin;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.repositories.AdminRepository;
import com.example.ecommerce.repositories.CustomerRepository;
import com.example.ecommerce.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final CustomerMapper customerMapper;


    @Autowired
    public UserService(CustomerRepository customerRepository, AdminRepository adminRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
        this.customerMapper = customerMapper;

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

    public CustomerDTO getCustomerByUsername(String username) {
        Optional<Customer> customer = customerRepository.findByAccount_UserName(username);
        if (customer.isPresent()) {
            Customer c = customer.get();
            return customerMapper.toDTO(c);
        }
        return null;
    }

    public void processOAuthPostLogin(String username) {
        Customer existUser = customerRepository.findByAccountUserName(username);

        if (existUser == null) {
            Account newUser = new Account();
            Customer c = new Customer();
            newUser.setUserName(username);
//            newUser.setProvider(Provider.GOOGLE);
//            newUser.setEnabled(true);
            c.setAccount(newUser);
            customerRepository.save(c);
        }

    }

    public void processOAuthPostLoginFacebook(String username) {
        Customer existUser = customerRepository.findByAccountUserName(username);

        if (existUser == null) {
            Account newUser = new Account();
            Customer c = new Customer();
            newUser.setUserName(username);
//            newUser.setProvider(Provider.GOOGLE);
//            newUser.setEnabled(true);
            c.setAccount(newUser);
            customerRepository.save(c);
        }

    }

}
