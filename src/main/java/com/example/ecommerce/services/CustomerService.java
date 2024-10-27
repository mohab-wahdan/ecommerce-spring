package com.example.ecommerce.services;

import com.example.ecommerce.dtos.CustomerDTO;
import com.example.ecommerce.mappers.CustomerMapper;
import com.example.ecommerce.models.Customer;

import com.example.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDTO)
                .orElse(null); // Handle not found case as needed
    }
    public Customer getCustomerOriginalById(Integer id) {
        return customerRepository.findById(id)
                .orElse(null); // Handle not found case as needed
    }

    public CustomerDTO updateCustomer(Integer id, CustomerDTO customerDTO) {
        if (!customerRepository.existsById(id)) {
            return null; // Handle not found case as needed
        }
        Customer customer = customerMapper.toEntity(customerDTO);
        customer.setId(id);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(updatedCustomer);
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }



}
