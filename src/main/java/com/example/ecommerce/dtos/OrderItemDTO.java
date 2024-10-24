package com.example.ecommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
    String getQuantity();
    String getPrice();
//    String getProductName();
=======
import java.math.BigDecimal;
>>>>>>> 6c390d291bb1ad329302c76764060f5e72760a81

@Data
@NoArgsConstructor
public class OrderItemDTO {
    private Integer subProductId;  // Refers to the SubProduct ID
    private Integer quantity;
    private BigDecimal price;
}
