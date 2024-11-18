package com.example.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilterRequest {
     String color;
     String size;
    BigDecimal maxPrice;
     BigDecimal minPrice;
    String searchkeyword;
     String gender;
     String category;
     String page ;
}
