package com.example.ecommerce.repositories;

import com.example.ecommerce.models.CartItems;
import com.example.ecommerce.models.EntitiesEmbeddedId.CustomerProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, CustomerProductId> {
    List<CartItems> findByCustomerId(Integer customerId);
}

