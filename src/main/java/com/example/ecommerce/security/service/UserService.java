package com.example.ecommerce.security.service;

import com.example.ecommerce.dtos.CustomerDTO;
import com.example.ecommerce.enums.Provider;
import com.example.ecommerce.mappers.CustomerMapper;
import com.example.ecommerce.models.Account;
import com.example.ecommerce.models.Admin;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.repositories.AccountRepository;
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
    private final AccountRepository accountRepository;

    @Autowired
    public UserService(CustomerRepository customerRepository, AdminRepository adminRepository, CustomerMapper customerMapper, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
        this.customerMapper = customerMapper;
        this.accountRepository = accountRepository;
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
        Account existUser = accountRepository.getByUserName(username);

        if (existUser == null) {
            Account newUser = new Account();
            newUser.setUserName(username);
            newUser.setProvider(Provider.GOOGLE);
            newUser.setEnabled(true);
            accountRepository.save(newUser);
        }

    }

    public void processOAuthPostLoginFacebook(String username) {
        Account existUser = accountRepository.getByUserName(username);

        if (existUser == null) {
            Account newUser = new Account();
            newUser.setUserName(username);
            newUser.setProvider(Provider.FACEBOOK);
            newUser.setEnabled(true);
            accountRepository.save(newUser);
        }

    }

}
