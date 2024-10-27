package com.example.ecommerce.services;

 import com.example.ecommerce.dtos.OrderItemDTO;
 import com.example.ecommerce.dtos.OrderItemViewDTO;
 import com.example.ecommerce.mappers.OrderItemCustomedMapper;
 import com.example.ecommerce.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import java.util.List;
 import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;


    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItemViewDTO> getAllOrderItemsByOrderId(Integer orderId) {
        return OrderItemCustomedMapper.convertEntityListToDTOList(orderItemRepository.findOrderItemsByOrderId(orderId));
    }

}
