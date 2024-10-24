package com.example.ecommerce.dtos;
import com.example.ecommerce.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
@Data
@NoArgsConstructor
public class OrderRequestDTO {
    private Integer customerId;
    private Status status;
    private Set<OrderItemDTO> orderItems;
}