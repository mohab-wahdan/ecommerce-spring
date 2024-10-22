package com.example.ecommerce.repositories;

import com.example.ecommerce.dtos.OrderItemDTO;
import com.example.ecommerce.models.EntitiesEmbeddedId.OrderProductId;
import com.example.ecommerce.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderProductId>{


//    List<OrderItemDTO>  getOrderItemsByOrderId(Integer id);
    List<OrderItemDTO>  findOrderItemsByOrderId(Integer id);
}
