package com.example.ecommerce.cartItems.mappers;

import com.example.ecommerce.cartItems.dto.CartItemsDTO;
import com.example.ecommerce.models.CartItems;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.models.SubProduct;
import com.example.ecommerce.cutomer.repository.CustomerRepository;
import com.example.ecommerce.product.repository.SubProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemsMapper {
    CustomerRepository customerRepository;
    SubProductRepository subProductRepository;
    CartItemsDTO cartItemsDTO;
    @Autowired
    CartItemsMapper(CustomerRepository customerRepository, SubProductRepository subProductRepository, CartItemsDTO cartItemsDTO) {
        this.customerRepository = customerRepository;
        this.subProductRepository = subProductRepository;
        this.cartItemsDTO = cartItemsDTO;
    }


    public CartItemsDTO toDto(CartItems cartItems) {
        if (cartItems == null) {
            return null;
        }
        return new CartItemsDTO(
                cartItems.getCustomer().getId(),  // Assuming you have a method getId() in Customer
                cartItems.getSubProduct().getId(), // Assuming you have a method getId() in SubProduct
                cartItems.getQuantity()
        );
    }

    public CartItems toEntity(CartItemsDTO cartItemsDTO) {
        if (cartItemsDTO == null) {
            return null;
        }
        CartItems cartItems = new CartItems();
        // Here you need to set the customer and subProduct by fetching from the repository
        // Example:
         Customer customer = customerRepository.findById(cartItemsDTO.getCustomerId()).orElse(null);
         SubProduct subProduct = subProductRepository.findById(cartItemsDTO.getSubProductId()).orElse(null);
        cartItems.setQuantity(cartItemsDTO.getQuantity());
        return cartItems;
    }
}
