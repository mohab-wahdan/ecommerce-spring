package com.example.ecommerce.cutomer.mappers;
import com.example.ecommerce.cartItems.dto.CartItemsDTO;
import com.example.ecommerce.cutomer.dto.CustomerDTO;
import com.example.ecommerce.cutomer.dto.CustomerViewDTO;
import com.example.ecommerce.order.mappers.OrderMapper;
import com.example.ecommerce.order.dto.OrderDTO;
import com.example.ecommerce.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    @Autowired
    private OrderMapper orderMapper;

//    @Autowired
//    public CustomerMapper(OrderMapper orderMapper) {
//        this.orderMapper = orderMapper;
//    }


    public CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
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
        dto.setAddress(customer.getAddress()); // Set the address
        dto.setAccount(customer.getAccount());

        // Map shopping cart items to CartItemsDTO
        Set<CartItemsDTO> cartItemsDTOs = customer.getShoppingCart().stream()
                .map(cartItem -> new CartItemsDTO(
                        cartItem.getCustomer().getId(),    // Get customer ID from the customer entity
                        cartItem.getSubProduct().getId(),  // Get subProduct ID from the subProduct entity
                        cartItem.getQuantity()))
                .collect(Collectors.toSet());
        dto.setShoppingCart(cartItemsDTOs);

// Use OrderMapper to map orders to OrderDTO
        Set<OrderDTO> orderDTOs = customer.getOrders().stream()
                .map(orderMapper::toDTO) // Call the predefined OrderMapper
                .collect(Collectors.toSet());
        dto.setOrders(orderDTOs); // Set orders in CustomerDTO

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
        customer.setAddress(dto.getAddress()); // Set the address
        Account account = dto.getAccount();
        account.setRoles("user");
        customer.setAccount(account);

        // Map CartItemsDTO to CartItems
        Set<CartItems> cartItems = dto.getShoppingCart().stream()
                .map(cartItemDTO -> {
                    CartItems cartItem = new CartItems();
                    // Set the Customer and SubProduct entities based on IDs from DTO
                    Customer customerEntity = new Customer();
                    customerEntity.setId(cartItemDTO.getCustomerId());
                    SubProduct subProductEntity = new SubProduct();
                    subProductEntity.setId(cartItemDTO.getSubProductId());

                    // Set the fields of CartItems
                    cartItem.setCustomer(customerEntity);
                    cartItem.setSubProduct(subProductEntity);
                    cartItem.setQuantity(cartItemDTO.getQuantity());
                    return cartItem;
                })
                .collect(Collectors.toSet());
        customer.setShoppingCart(cartItems);

       // Map OrderDTO to Order using OrderMapper
        Set<Order> orders = dto.getOrders().stream()
                .map(orderMapper::toEntity) // Call the predefined OrderMapper
                .collect(Collectors.toSet());
        customer.setOrders(orders); // Set orders in Customer

        return customer;
    }
    public List<CustomerViewDTO> fromEntityToCustomerViewDTO(Optional<List<Customer>> customersOptional) {
        if (customersOptional.isEmpty()) {
            return null;
        }
        List<CustomerViewDTO> customerViewDTOList = new ArrayList<>();

        if (customersOptional.isPresent()) {
            List<Customer> customersList = customersOptional.get();

            for (Customer customer : customersList) {
                CustomerViewDTO dto = CustomerViewDTO.fromCustomer(customer);
                customerViewDTOList.add(dto);
            }
        }

        return customerViewDTOList;
    }
}
