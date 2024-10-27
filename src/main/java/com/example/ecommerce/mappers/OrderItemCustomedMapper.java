package com.example.ecommerce.mappers;

import com.example.ecommerce.dtos.OrderItemDTO;
import com.example.ecommerce.dtos.OrderItemViewDTO;
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
