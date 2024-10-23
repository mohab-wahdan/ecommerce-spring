package com.example.ecommerce.dtos;

import com.example.ecommerce.enums.Status;

public interface OrderViewDTO {

    Integer getId();
    String getStatus();
    String getCreatedAt();  // Assuming you convert Date to String format
    //String getDestination();

}