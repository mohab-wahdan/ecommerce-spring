package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.OrderItemDTO;
import com.example.ecommerce.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderItemsController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemsController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    @GetMapping("/order-items/{id}")
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItemsOfSpecificOrderId(@PathVariable Integer id){
        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItemsByOrderId(id);
        return ResponseEntity.ok(orderItems);
    }

}
