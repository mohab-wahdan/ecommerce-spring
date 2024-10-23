package com.example.ecommerce.dtos;

import com.example.ecommerce.models.Product;
import jakarta.servlet.http.HttpServletRequest;

public class ProductViewDTO {
    private Integer id;
    private String name;

    public ProductViewDTO() {
    }

    public ProductViewDTO(Integer id, String name) {
        this.id = id;
        this.name = name;

    }

    public static ProductViewDTO fromProduct(Product product) {
        return new ProductViewDTO(
                product.getId(),
                product.getName()
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
