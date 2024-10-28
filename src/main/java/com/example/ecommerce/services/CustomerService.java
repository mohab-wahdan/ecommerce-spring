package com.example.ecommerce.services;

import com.example.ecommerce.dtos.CustomerDTO;
import com.example.ecommerce.dtos.CustomerViewDTO;
import com.example.ecommerce.mappers.CustomerMapper;
import com.example.ecommerce.models.Customer;

import com.example.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        customerDTO.getAccount().setPassword(passwordEncoder.encode( customerDTO.getAccount().getPassword() ));
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }


    public List<CustomerViewDTO> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerMapper.fromEntityToCustomerViewDTO(Optional.of(customerList));
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


    public CustomerViewDTO getCustomerByIdForAdmin(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);

        return CustomerViewDTO.fromCustomer(customer.get());
    }
}
