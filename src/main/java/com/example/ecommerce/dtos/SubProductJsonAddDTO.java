package com.example.ecommerce.dtos;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubProductJsonAddDTO {
    private int id;
    private String productName;
    private BigDecimal price;
    private String imageURL;
    private String color;
    private String size;
    private String description;
    private int stock;
    private int quantity;
    private String subCategoryName;
    private int productId;
}
