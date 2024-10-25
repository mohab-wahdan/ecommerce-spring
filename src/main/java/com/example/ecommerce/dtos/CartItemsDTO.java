package com.example.ecommerce.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CartItemsDTO implements Serializable {
    private Integer customerId;     // Assuming customer ID is Long
    private Integer subProductId;   // Assuming subProduct ID is Long
    private Integer quantity;

}



