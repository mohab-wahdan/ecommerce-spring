package com.example.ecommerce.category.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryDTO {
    private Integer id;
    private Integer categoryID;
    private String name;
}