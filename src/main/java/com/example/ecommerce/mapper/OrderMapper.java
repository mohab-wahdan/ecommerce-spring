package com.example.ecommerce.mapper;

import com.example.ecommerce.dtos.OrderViewDTO;
import com.example.ecommerce.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper {

//    OrderMapper INSTANCE = Mapper.getMapper(OrderMapper.class);

    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss") // format the date to String
    OrderViewDTO toOrderViewDTO(Order order);
}
