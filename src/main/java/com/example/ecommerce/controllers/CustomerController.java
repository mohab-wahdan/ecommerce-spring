package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.CustomerViewDTO;
import com.example.ecommerce.services.CustomerService;

import com.example.ecommerce.dtos.CustomerDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        System.out.println("Received customer data: " + customerDTO);
        return ResponseEntity.ok(createdCustomer);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<String> checkEmailExists(@PathVariable String email) {
        boolean exists = customerService.doesEmailExist(email);

        if (exists) {
            return ResponseEntity.ok("exists");
        } else {
            return ResponseEntity.ok("available");
        }
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<String> checkUsernameExists(@PathVariable String username) {
        boolean exists = customerService.doesUsernameExist(username);

        if (exists) {
            return ResponseEntity.ok("exists");
        } else {
            return ResponseEntity.ok("available");
        }
    }
    @GetMapping("/phonenumber/{phonenumber}")
    public ResponseEntity<String> checkPhoneNumberExists(@PathVariable String phonenumber) {
        boolean exists = customerService.doesPhoneNumberExist(phonenumber);

        if (exists) {
            return ResponseEntity.ok("exists");
        } else {
            return ResponseEntity.ok("available");
        }
    }
    @GetMapping
    public ResponseEntity<List<CustomerViewDTO>> getAllCustomers() {
        List<CustomerViewDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }
    @GetMapping("/customerUsername/{username}")
    public ResponseEntity<CustomerViewDTO> getCustomerByUsername(@PathVariable String username) {
        CustomerViewDTO customer = customerService.getCustomerByUsername(username);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }
    @GetMapping("/customerId/{id}")
    public ResponseEntity<CustomerViewDTO> getCustomerByIdForAdmin(@PathVariable Integer id) {
        CustomerViewDTO customer = customerService.getCustomerByIdForAdmin(id);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return updatedCustomer != null ? ResponseEntity.ok(updatedCustomer) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCustomers() {
        customerService.deleteAllCustomers();
        return ResponseEntity.noContent().build();
    }
}
