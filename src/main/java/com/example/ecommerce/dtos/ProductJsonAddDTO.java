package com.example.ecommerce.dtos;

import com.example.ecommerce.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductJsonAddDTO {
    private String description;
    private String name;
    private Gender gender;
    private Integer subCategoryId;
}
