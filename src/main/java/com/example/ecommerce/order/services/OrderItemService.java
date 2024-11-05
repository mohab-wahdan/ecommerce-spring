package com.example.ecommerce.order.services;

 import com.example.ecommerce.order.dto.OrderItemViewDTO;
 import com.example.ecommerce.order.mappers.OrderItemCustomedMapper;
 import com.example.ecommerce.order.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import java.util.List;

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
