// OrderMapper.java
package com.example.ecommerce.mapper;

import com.example.ecommerce.dtos.OrderDTO;
import com.example.ecommerce.dtos.OrderItemDTO;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.models.Order;
import com.example.ecommerce.models.OrderItem;
import com.example.ecommerce.models.SubProduct;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setCustomerId(order.getCustomer().getId());

        // Map OrderItems to OrderItemDTO
        Set<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemDTO(
                        orderItem.getSubProduct().getId(),
                        orderItem.getOrder().getId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice()))
                .collect(Collectors.toSet());
        dto.setOrderItems(orderItemDTOs);

        return dto;
    }

    public Order toEntity(OrderDTO dto) {
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

        // Map OrderItemDTO to OrderItem
        Set<OrderItem> orderItems = dto.getOrderItems().stream()
                .map(orderItemDTO -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(orderItemDTO.getQuantity());
                    orderItem.setPrice(orderItemDTO.getPrice());

                    // Set the SubProduct entity based on the productId from DTO
                    SubProduct subProduct = new SubProduct();
                    subProduct.setId(orderItemDTO.getProductId());
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
