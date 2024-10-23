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

//public String updateCustomer(Long id, Customer customer) {
//        customerRepository.save(customer);
//        return "Customer "+ +customer.getId()+" updated successfully";
//
//}

    public String updateCustomer(Long id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            return "Customer with ID: " + id + " not found. Update failed.";
        }

        customer.setId(Math.toIntExact(id)); // Ensure the ID remains the same
        customerRepository.save(customer);
        return "Customer with ID: " + id + " updated successfully.";
    }



//    public String deleteCustomer(Long id) {
//        customerRepository.deleteById(id);
//        return "Customer with ID : "+id + " deleted successfully";
//    }

    public String deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            return "Customer with ID: " + id + " does not exist or has already been deleted.";
        }

        customerRepository.deleteById(id);
        return "Customer with ID: " + id + " deleted successfully.";
    }

//
//    public String deleteAllCustomers() {
//        customerRepository.deleteAll();
//        return "All customers deleted successfully";
//    }

    public String deleteAllCustomers() {
        if (customerRepository.count() == 0) {
            return "No customers to delete. The list is already empty.";
        }

        customerRepository.deleteAll();
        return "All customers deleted successfully.";
    }

}
