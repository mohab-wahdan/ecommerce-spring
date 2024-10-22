package com.example.ecommerce.models.EntitiesEmbeddedId;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class OrderProductId implements Serializable {
    private Integer subProduct;
    private Integer order;
}
