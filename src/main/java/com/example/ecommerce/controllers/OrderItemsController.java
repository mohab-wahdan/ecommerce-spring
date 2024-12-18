package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.OrderItemDTO;
import com.example.ecommerce.dtos.OrderItemViewDTO;
import com.example.ecommerce.models.OrderItem;
import com.example.ecommerce.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemsController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemsController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    @GetMapping("/orderId/{id}")
    public ResponseEntity<List<OrderItemViewDTO>> getAllOrderItemsOfSpecificOrderId(@PathVariable Integer id){
        List<OrderItemViewDTO> orderItems = orderItemService.getAllOrderItemsByOrderId(id);
        return ResponseEntity.ok(orderItems);
    }

}