package com.example.ecommerce.services;

 import com.example.ecommerce.dtos.OrderItemDTO;
 import com.example.ecommerce.models.OrderItem;
 import com.example.ecommerce.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import java.util.List;

@Service
public class OrderItemService {

    OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItemDTO> getAllOrderItemsByOrderId(Integer orderId) {
        return orderItemRepository.findOrderItemsByOrderId(orderId);
    }

}
