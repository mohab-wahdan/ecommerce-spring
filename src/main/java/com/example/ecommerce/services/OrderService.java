package com.example.ecommerce.Services;

import com.example.ecommerce.enums.Status;
import com.example.ecommerce.models.*;
import com.example.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository ;


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private Order createPendingOrder(Customer customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setCreatedAt(new Date());
        order.setStatus(Status.PENDING);
        orderRepository.save(order);
        return order;
    }

    public List<Order> getAllOrdersOfSpecificCustomer(String id) {
        Integer customerId = Integer.parseInt(id);
        return orderRepository.findOrdersByCustomerId(customerId);
    }
    public void updateOrderStatus(int id, Status status) {
        int result = orderRepository.updateOrderStatus(id, status.name());
    }

    public Optional<Order> getOrderById(String orderId) {
        Integer orderIdInt = Integer.parseInt(orderId);
        return orderRepository.findById(orderIdInt);
    }
}
