package com.example.ecommerce.services;

 import com.example.ecommerce.dtos.OrderItemDTO;
 import com.example.ecommerce.mappers.OrderItemMapper;
 import com.example.ecommerce.mappers.OrderMapper;
 import com.example.ecommerce.models.OrderItem;
 import com.example.ecommerce.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import java.util.List;
 import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;


    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository,OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    public List<OrderItemDTO> getAllOrderItemsByOrderId(Integer orderId) {
        return orderItemRepository.findOrderItemsByOrderId(orderId).stream().map(orderItemMapper::toDTO).collect(Collectors.toList());
    }

}
