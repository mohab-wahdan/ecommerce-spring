//package com.example.ecommerce.controllers;
//import com.example.ecommerce.Services.CartItemsServices;
//import com.example.ecommerce.models.CartItems;
//import com.example.ecommerce.models.EntitiesEmbeddedId.CustomerProductId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/cartItems")
//public class CartItemsController {
//
//    @Autowired
//    private CartItemsServices cartItemsService;
//
//    @GetMapping
//    public ResponseEntity<List<CartItems>> getAllCartItems() {
//        List<CartItems> cartItems = cartItemsService.getAllCartItems();
//        return ResponseEntity.ok(cartItems);
//    }
//
//    @PostMapping
//    public ResponseEntity<CartItems> addOrUpdateCartItem(@RequestBody CartItems cartItem) {
//        CartItems savedCartItem = cartItemsService.addOrUpdateCartItem(cartItem);
//        return ResponseEntity.ok(savedCartItem);
//    }
//
//    @GetMapping("/{customerId}/{subProductId}")
//    public ResponseEntity<CartItems> getCartItemById(@PathVariable Integer customerId, @PathVariable Integer subProductId) {
//        CustomerProductId id = new CustomerProductId(customerId, subProductId);
//        Optional<CartItems> cartItem = cartItemsService.getCartItemById(id);
//        return cartItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//
//    @DeleteMapping("/{customerId}/{subProductId}")
//    public ResponseEntity<Void> deleteCartItem(@PathVariable Integer customerId, @PathVariable Integer subProductId) {
//        CustomerProductId id = new CustomerProductId(customerId, subProductId);
//        cartItemsService.deleteCartItem(id);
//        return ResponseEntity.noContent().build();
//    }
//
//
//}
