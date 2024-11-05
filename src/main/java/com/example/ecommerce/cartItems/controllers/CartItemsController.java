package com.example.ecommerce.cartItems.controllers;



import com.example.ecommerce.cartItems.dto.CartItemsDTO;
import com.example.ecommerce.product.dto.SubProductDTO;
import com.example.ecommerce.models.EntitiesEmbeddedId.CustomerProductId;
import com.example.ecommerce.cartItems.services.CartItemsService;
import com.example.ecommerce.product.services.SubProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartItems")
public class CartItemsController {


    private CartItemsService cartItemsService;
    private SubProductService subProductService;

    @Autowired
    public CartItemsController (CartItemsService cartItemsService, SubProductService subProductService) {
        this.cartItemsService = cartItemsService;
        this.subProductService= subProductService;
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

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CartItemsDTO>> getCartByCustomerId(@PathVariable Integer customerId) {
        try {
            List<CartItemsDTO> cartItems = cartItemsService.getCartByCustomerId(customerId);
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/subProduct/{subProductId}")
    public ResponseEntity<Optional<SubProductDTO>> getProducts(@PathVariable Integer subProductId) {
        try {
            Optional<SubProductDTO> subProduct =  subProductService.findSubProductDTOById(subProductId);
            return new ResponseEntity<>(subProduct, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/subProductDTO/{id}")
    public ResponseEntity<SubProductDTO> getSubProductDTOById(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build(); // Or handle the missing ID case as needed
        }

        return subProductService.findSubProductDTOById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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