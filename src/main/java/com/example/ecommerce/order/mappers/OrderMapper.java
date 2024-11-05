// OrderMapper.java
package com.example.ecommerce.order.mappers;

import com.example.ecommerce.order.dto.OrderDTO;
import com.example.ecommerce.order.dto.OrderItemCustomerDTO;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.models.Order;
import com.example.ecommerce.models.OrderItem;
import com.example.ecommerce.models.SubProduct;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public  OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setCustomerId(order.getCustomer().getId());

        // Map OrderItems to OrderItemCustomerDTO
        Set<OrderItemCustomerDTO> orderItemCustomerDTOS = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemCustomerDTO(
                        orderItem.getSubProduct().getId(),
                        orderItem.getOrder().getId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice()))
                .collect(Collectors.toSet());
        dto.setOrderItems(orderItemCustomerDTOS);

        return dto;
    }

    public  Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setId(dto.getId());
        order.setStatus(dto.getStatus());
        order.setCreatedAt(dto.getCreatedAt());

        // You would typically fetch the Customer entity from the database
        // using the customerId from the DTO, for now, we'll create a new Customer object
        Customer customer = new Customer();
        customer.setId(dto.getCustomerId());
        order.setCustomer(customer);

        // Map OrderItemCustomerDTO to OrderItem
        Set<OrderItem> orderItems = dto.getOrderItems().stream()
                .map(orderItemCustomerDTO -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(orderItemCustomerDTO.getQuantity());
                    orderItem.setPrice(orderItemCustomerDTO.getPrice());

                    // Set the SubProduct entity based on the productId from DTO
                    SubProduct subProduct = new SubProduct();
                    subProduct.setId(orderItemCustomerDTO.getProductId());
                    orderItem.setSubProduct(subProduct);

                    // Set the Order entity based on the orderId from DTO
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toSet());
        order.setOrderItems(orderItems);

        return order;
    }
}