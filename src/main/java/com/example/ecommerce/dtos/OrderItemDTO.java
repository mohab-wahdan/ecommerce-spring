// OrderItemDTO.java
package com.example.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Integer productId;
    private Integer orderId;
    private Integer quantity;
    private BigDecimal price;
}