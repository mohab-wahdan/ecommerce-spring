package com.example.ecommerce.Entities.EntitiesEmbeddedId;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class CustomerProductId implements Serializable {
    private Integer customer;
    private Integer subProduct;
}
