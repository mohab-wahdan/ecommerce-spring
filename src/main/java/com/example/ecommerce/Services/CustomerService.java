package com.example.ecommerce.services;


import com.example.ecommerce.models.Customer;
import com.example.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String addCustomer(Customer customer) {
        customer.getAccount().setPassword(passwordEncoder.encode(customer.getAccount().getPassword()));
        customerRepository.save(customer);
        return "Customer "+ +customer.getId()+" added successfully";
    }
    public Customer getCustomerById(Integer id) {
        customerRepository.findById(id);
        return customerRepository.findById(id).get();
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public boolean login(String username, String password) {
        Optional<Customer> customer = customerRepository.
                findByAccount_UserNameAndAccount_Password(username, password);
        return customer.isPresent();
    }

}
