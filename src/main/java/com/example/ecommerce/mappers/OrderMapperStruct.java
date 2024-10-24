package com.example.ecommerce.mappers;


import com.example.ecommerce.dtos.OrderViewDTO;
import com.example.ecommerce.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapperStruct {

    OrderMapperStruct INSTANCE = Mappers.getMapper(OrderMapperStruct.class);

    @Mapping(target = "destination", expression = "java(getDestination(order))")
    OrderViewDTO toDTO(Order order);
    Order toModel(OrderViewDTO orderViewDTO);

    default String getDestination(Order order) {
        return order.getCustomer().getAddress().getCity()
                + "-" + order.getCustomer().getAddress().getStreet()
                + "-" + order.getCustomer().getAddress().getDescription();
    }
}
