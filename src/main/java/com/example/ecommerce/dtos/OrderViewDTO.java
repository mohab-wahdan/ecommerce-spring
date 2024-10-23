package com.example.ecommerce.dtos;

import com.example.ecommerce.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderViewDTO {
    private Integer id;
    private Status status ;
    private Date createdAt;
    private String  destination;
}