package com.example.ecommerce.controllers;

import com.example.ecommerce.Services.CartItemsService;
import com.example.ecommerce.dtos.CartItemsDTO;
import com.example.ecommerce.models.EntitiesEmbeddedId.CustomerProductId;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartItems")
public class CartItemsController {


    private CartItemsService cartItemsService;

    @Autowired
    public CartItemsController (CartItemsService cartItemsService) {
        this.cartItemsService = cartItemsService;
    }

    @GetMapping
    public ResponseEntity<List<CartItemsDTO>> getAllCartItems() {
        List<CartItemsDTO> cartItems = cartItemsService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping
    public ResponseEntity<CartItemsDTO> deleteAllCartItems() {
        cartItemsService.deleteAllCartItems();
        return ResponseEntity.status(204).build();
    }

    // Get a cart item by its composite key
    @GetMapping("/{customerId}/{subProductId}")
    public ResponseEntity<CartItemsDTO> getCartItemById(
            @PathVariable Integer customerId,
            @PathVariable Integer subProductId) {

        // Create the composite key from the path variables
        CustomerProductId id = new CustomerProductId(customerId, subProductId);

        try {
            CartItemsDTO cartItemsDTO = cartItemsService.getCartItemById(id);
            return new ResponseEntity<>(cartItemsDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            // Return 404 if the cart item is not found
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{customerId}/{subProductId}")
    public ResponseEntity<Void> deleteCartItem(
            @PathVariable Integer customerId,
            @PathVariable Integer subProductId) {

        // Create the composite key from the path variables
        CustomerProductId id = new CustomerProductId(customerId, subProductId);

        try {
            cartItemsService.deleteCartItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (EntityNotFoundException e) {
            // Return 404 if the cart item is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Update a cart item by its composite key
    @PutMapping("/{customerId}/{subProductId}")
    public ResponseEntity<CartItemsDTO> updateCartItem(
            @PathVariable Integer customerId,
            @PathVariable Integer subProductId,
            @RequestBody CartItemsDTO cartItemsDTO) {

        // Create the composite key from the path variables
        CustomerProductId id = new CustomerProductId(customerId, subProductId);

        try {
            CartItemsDTO updatedCartItemsDTO = cartItemsService.updateCartItem(id, cartItemsDTO);
            return new ResponseEntity<>(updatedCartItemsDTO, HttpStatus.OK); // 200 OK
        } catch (EntityNotFoundException e) {
            // Return 404 if the cart item is not found
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    // Add a new cart item
    @PostMapping
    public ResponseEntity<CartItemsDTO> addCartItem(@RequestBody CartItemsDTO cartItemsDTO) {
        try {
            CartItemsDTO savedCartItemsDTO = cartItemsService.addCartItem(cartItemsDTO);
            return new ResponseEntity<>(savedCartItemsDTO, HttpStatus.CREATED); // 201 Created
        } catch (EntityNotFoundException e) {
            // Return 404 if the customer or subProduct is not found
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}