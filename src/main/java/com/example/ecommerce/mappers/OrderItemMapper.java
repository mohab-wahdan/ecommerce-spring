package com.example.ecommerce.mappers;

import com.example.ecommerce.dtos.OrderItemDTO;
import com.example.ecommerce.dtos.OrderViewDTO;
import com.example.ecommerce.models.Order;
import com.example.ecommerce.models.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO toDTO(OrderItem orderItem);
    OrderItem toModel(OrderItemDTO orderItemDTO);
}
