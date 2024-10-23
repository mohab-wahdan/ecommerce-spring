package com.example.ecommerce.Services;

import com.example.ecommerce.dtos.CartItemsDTO;
import com.example.ecommerce.mapper.CartItemsMapper;
import com.example.ecommerce.models.CartItems;
import com.example.ecommerce.repositories.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemsService {

    private CartItemsRepository cartItemsRepository;
    private CartItemsMapper cartItemsMapper;

    @Autowired
    public CartItemsService(CartItemsRepository cartItemsRepository, CartItemsMapper cartItemsMapper) {
        this.cartItemsMapper=cartItemsMapper;
        this.cartItemsRepository = cartItemsRepository;

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

//    // Delete a specific cart item
//    public boolean deleteCartItem(Integer id) {
//        if (cartItemsRepository.existsById(id)) {
//            cartItemsRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }

    // Delete all cart items
    public void deleteAllCartItems() {
        cartItemsRepository.deleteAll();
    }
 
}
