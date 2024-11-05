package com.example.ecommerce.cartItems.repository;

import com.example.ecommerce.models.CartItems;
import com.example.ecommerce.models.EntitiesEmbeddedId.CustomerProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, CustomerProductId> {

    List<CartItems> findByCustomer_Id(Integer id);
    List<CartItems> findByCustomerId(Integer customerId);
}

