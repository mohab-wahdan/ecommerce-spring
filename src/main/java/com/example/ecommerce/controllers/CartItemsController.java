package com.example.ecommerce.controllers;

import com.example.ecommerce.Services.CartItemsService;
import com.example.ecommerce.dtos.CartItemsDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @PostMapping
//    public ResponseEntity<CartItemsDTO> createCartItem(@RequestBody CartItemsDTO cartItemsDTO) {
//        CartItemsDTO createdCartItem = cartItemsService.createCartItem(cartItemsDTO);
//        return ResponseEntity.status(201).body(createdCartItem);
//    }


//    @PutMapping
//    public ResponseEntity<CartItemsDTO> updateCartItem(@RequestBody CartItemsDTO cartItemsDTO) {
//        CartItemsDTO createdCartItem = cartItemsService.updateCartItem(cartItemsDTO);
//        return ResponseEntity.status(201).body(createdCartItem);
//    }
//    @DeleteMapping
//    public ResponseEntity<CartItemsDTO> deleteCartItem(Integer cartItemsId) {
//        cartItemsService.deleteCartItem(cartItemsId);
//        return ResponseEntity.status(204).build();
//    }
@DeleteMapping
    public ResponseEntity<CartItemsDTO> deleteAllCartItems() {
        cartItemsService.deleteAllCartItems();
        return ResponseEntity.status(204).build();
}
}
