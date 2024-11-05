package com.example.ecommerce.order.mappers;

import com.example.ecommerce.order.dto.OrderItemViewDTO;
import com.example.ecommerce.models.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class OrderItemCustomedMapper {
    public static List<OrderItemViewDTO> convertEntityListToDTOList(List<OrderItem> orderItemList){
        List<OrderItemViewDTO> orderItemDTOList = new ArrayList<>();
        for(OrderItem orderItem:orderItemList){
            orderItemDTOList.add(convertEntityToDTO(orderItem));
        }
        return orderItemDTOList;
    }
    public static OrderItemViewDTO convertEntityToDTO(OrderItem order){

        return new OrderItemViewDTO(order.getQuantity().toString(),order.getPrice().toString(),order.getSubProduct().getProduct().getName());
    }

}
