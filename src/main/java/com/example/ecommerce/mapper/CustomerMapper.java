package com.example.ecommerce.mapper;


import com.example.ecommerce.dtos.CustomerDTO;
import com.example.ecommerce.models.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setCreditLimit(customer.getCreditLimit());
        dto.setDateOfBirth(customer.getDateOfBirth());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setJob(customer.getJob());
        // Map Address and Account if needed
        return dto;
    }

    public Customer toEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setCreditLimit(dto.getCreditLimit());
        customer.setDateOfBirth(dto.getDateOfBirth());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setJob(dto.getJob());
        // Set Address and Account if needed
        return customer;
    }
}
