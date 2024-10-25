package com.example.ecommerce.services;

import com.example.ecommerce.dtos.CartItemsDTO;
import com.example.ecommerce.mappers.CartItemsMapper;
import com.example.ecommerce.models.CartItems;
import com.example.ecommerce.models.EntitiesEmbeddedId.CustomerProductId;
import com.example.ecommerce.repositories.CartItemsRepository;
import com.example.ecommerce.repositories.CustomerRepository;
import com.example.ecommerce.repositories.SubProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemsService {

    private CartItemsRepository cartItemsRepository;
    private CartItemsMapper cartItemsMapper;
    private CustomerRepository customerRepository;
    private SubProductRepository subProductRepository;

    @Autowired
    public CartItemsService(CartItemsRepository cartItemsRepository, CartItemsMapper cartItemsMapper, CustomerRepository customerRepository, SubProductRepository subProductRepository) {
        this.cartItemsMapper=cartItemsMapper;
        this.cartItemsRepository = cartItemsRepository;
        this.customerRepository = customerRepository;
        this.subProductRepository = subProductRepository;

    }

    public List<CartItemsDTO> getAllCartItems() {
        return cartItemsRepository.findAll()
                .stream()
                .map(cartItemsMapper::toDto)
                .collect(Collectors.toList());
    }

    public CartItemsDTO createCartItem(CartItemsDTO cartItemsDTO) {
        CartItems cartItems = cartItemsMapper.toEntity(cartItemsDTO);
        cartItems = cartItemsRepository.save(cartItems);
        return cartItemsMapper.toDto(cartItems);
    }

    public CartItemsDTO updateCartItem(CartItemsDTO cartItemsDTO) {
        CartItems cartItems = cartItemsMapper.toEntity(cartItemsDTO);
        cartItems = cartItemsRepository.save(cartItems);
        return cartItemsMapper.toDto(cartItems);
    }


    // Delete all cart items
    public void deleteAllCartItems() {
        cartItemsRepository.deleteAll();
    }

    public CartItemsDTO getCartItemById(CustomerProductId id) {
        // Assuming you have a repository to fetch CartItems
        Optional<CartItems> cartItemOptional = cartItemsRepository.findById(id);

        // Check if the cart item exists
        if (cartItemOptional.isPresent()) {
            CartItems cartItem = cartItemOptional.get();

            // Map CartItems to CartItemsDTO
            CartItemsDTO cartItemsDTO = new CartItemsDTO();
            cartItemsDTO.setCustomerId(cartItem.getCustomer().getId()); // Assuming Customer has getId()
            cartItemsDTO.setSubProductId(cartItem.getSubProduct().getId()); // Assuming SubProduct has getId()
            cartItemsDTO.setQuantity(cartItem.getQuantity());

            return cartItemsDTO;
        } else {
            // Handle the case where the cart item does not exist
            throw new EntityNotFoundException("Cart item not found for ID: " + id);
        }
    }

    // Method to delete a cart item
    public void deleteCartItem(CustomerProductId id) {
        // Check if the cart item exists
        if (!cartItemsRepository.existsById(id)) {
            throw new EntityNotFoundException("Cart item not found for ID: " + id);
        }
        // Delete the cart item
        cartItemsRepository.deleteById(id);
    }

    // Method to update a cart item
    public CartItemsDTO updateCartItem(CustomerProductId id, CartItemsDTO cartItemsDTO) {
        // Check if the cart item exists
        CartItems existingCartItem = cartItemsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found for ID: " + id));

        // Update the fields as needed
        existingCartItem.setQuantity(cartItemsDTO.getQuantity());
        // Add any other fields you wish to update

        // Save the updated cart item
        CartItems updatedCartItem = cartItemsRepository.save(existingCartItem);

        // Map updated CartItems to CartItemsDTO
        CartItemsDTO updatedCartItemsDTO = new CartItemsDTO();
        updatedCartItemsDTO.setCustomerId(updatedCartItem.getCustomer().getId());
        updatedCartItemsDTO.setSubProductId(updatedCartItem.getSubProduct().getId());
        updatedCartItemsDTO.setQuantity(updatedCartItem.getQuantity());

        return updatedCartItemsDTO;
    }

    // Method to add a new cart item
    public CartItemsDTO addCartItem(CartItemsDTO cartItemsDTO) {
        // Create a new CartItems entity from the DTO
        CartItems newCartItem = new CartItems();

        // Set properties
        newCartItem.setQuantity(cartItemsDTO.getQuantity());

        // Assuming you have methods to retrieve the customer and subProduct by their IDs
        // Adjust these methods based on your actual implementation
        newCartItem.setCustomer(customerRepository.findById(cartItemsDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found for ID: " + cartItemsDTO.getCustomerId())));

        newCartItem.setSubProduct(subProductRepository.findById(cartItemsDTO.getSubProductId())
                .orElseThrow(() -> new EntityNotFoundException("SubProduct not found for ID: " + cartItemsDTO.getSubProductId())));

        // Save the new cart item
        CartItems savedCartItem = cartItemsRepository.save(newCartItem);

        // Map saved CartItems to CartItemsDTO
        CartItemsDTO savedCartItemsDTO = new CartItemsDTO();
        savedCartItemsDTO.setCustomerId(savedCartItem.getCustomer().getId());
        savedCartItemsDTO.setSubProductId(savedCartItem.getSubProduct().getId());
        savedCartItemsDTO.setQuantity(savedCartItem.getQuantity());

        return savedCartItemsDTO;
    }

}