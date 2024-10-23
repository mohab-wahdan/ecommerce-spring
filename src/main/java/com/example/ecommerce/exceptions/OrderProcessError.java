package com.example.ecommerce.exceptions;


import com.example.ecommerce.dtos.SubProductDTO;
import com.example.ecommerce.models.Order;

public class OrderProcessError {
    private Order order;
    private SubProductDTO subProductDTO;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public SubProductDTO getSubProductDTO() {
        return subProductDTO;
    }

    public void setSubProductDTO(SubProductDTO subProductDTO) {
        this.subProductDTO = subProductDTO;
    }
}