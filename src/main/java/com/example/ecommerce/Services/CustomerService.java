package com.example.ecommerce.Services;


import com.example.ecommerce.models.Customer;
import com.example.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String addCustomer(Customer customer) {
        customerRepository.save(customer);
        return "Customer "+ +customer.getId()+" added successfully";
    }
    public Customer getCustomerById(Long id) {
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
