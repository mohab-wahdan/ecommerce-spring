package com.example.ecommerce.controllers;


import com.example.ecommerce.Services.CustomerService;
import com.example.ecommerce.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public String addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Boolean>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        boolean success = customerService.login(username, password);
        Map<String, Boolean> response = Collections.singletonMap("success", success);
        return ResponseEntity.ok(response);
    }



    @PutMapping("/{customerID}")
    public String updateCustomer(@PathVariable Long customerID, @RequestBody Customer customer) {
       return customerService.updateCustomer(customerID,customer);

    }


    @DeleteMapping("/{customerID}")
    public String deleteCustomer(@PathVariable Long customerID) {
        return customerService.deleteCustomer(customerID);

    }


    @DeleteMapping
    public String deleteAllCustomers() {
    return customerService.deleteAllCustomers();

}

}
