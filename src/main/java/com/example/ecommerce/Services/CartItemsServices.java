package com.example.ecommerce.Services;

import com.example.ecommerce.models.CartItems;
import com.example.ecommerce.models.EntitiesEmbeddedId.CustomerProductId;
import com.example.ecommerce.repositories.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemsServices {


    @Autowired
    private CartItemsRepository cartItemsRepository;

    public List<CartItems> getAllCartItems() {
        return cartItemsRepository.findAll();
    }

    public Optional<CartItems> getCartItemById(CustomerProductId id) {
        return cartItemsRepository.findById(id);
    }

    public CartItems addOrUpdateCartItem(CartItems cartItem) {
        return cartItemsRepository.save(cartItem);
    }

    public void deleteCartItem(CustomerProductId id) {
        cartItemsRepository.deleteById(id);
    }
}
