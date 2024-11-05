package com.example.ecommerce.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDTO {
    private Integer subProductId;  // Refers to the SubProduct ID
    private Integer quantity;
    private BigDecimal price;
}
