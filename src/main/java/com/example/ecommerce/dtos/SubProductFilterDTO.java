package com.example.ecommerce.dtos;


import com.example.ecommerce.enums.Color;
import com.example.ecommerce.enums.Gender;
import com.example.ecommerce.enums.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Setter
@ToString
@NoArgsConstructor
public class SubProductFilterDTO {
    private String searchKeyword;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Size size;
    private Color color;
    private Gender gender;
    private Integer categoryId;
    private String categoryName;
    private Integer pageNumber;
    private Integer pageSize = 5;
    private Boolean isDeleted = Boolean.FALSE;
    private Boolean isNewArrival;
    private Boolean inStock = Boolean.TRUE;
    private String subCategoryName;
}