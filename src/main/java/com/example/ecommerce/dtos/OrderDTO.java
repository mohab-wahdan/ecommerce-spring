// OrderDTO.java
package com.example.ecommerce.dtos;

import com.example.ecommerce.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer id;
    private Status status;
    private Date createdAt;
    private Integer customerId;
    private Set<OrderItemDTO> orderItems;
}