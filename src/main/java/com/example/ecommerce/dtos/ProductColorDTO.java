package com.example.ecommerce.dtos;

import com.example.ecommerce.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductColorDTO {
    private String name;
    private Color color;


    @Override
    public String toString() {
        return "product '" + name + '\'' +
                " with '" + color.name() + " color" + '\''
                ;
    }
}

