package com.example.ecommerce.controllers;
import com.example.ecommerce.dtos.OrderRequestDTO;
import com.example.ecommerce.dtos.OrderViewDTO;
import com.example.ecommerce.enums.Status;
import com.example.ecommerce.models.Order;
import com.example.ecommerce.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }



    @PatchMapping("/{orderId}/status/{status}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Integer orderId, @PathVariable String status) {
        try {
            Status orderStatus = Status.valueOf(status.toUpperCase());
            orderService.updateOrderStatus(orderId, orderStatus);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        Order createdOrder = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderViewDTO> getOrderById(@PathVariable Integer orderId) {
        try {
            OrderViewDTO order = orderService.getOrderById(orderId);
            if (order != null) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderViewDTO>> getAllOrdersOfWithCustomerId(@PathVariable Integer customerId) {
        try {
            List<OrderViewDTO> orders = orderService.getAllOrdersOfSpecificCustomer(customerId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}