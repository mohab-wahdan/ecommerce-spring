package com.example.ecommerce.models;


import com.example.ecommerce.models.EntitiesEmbeddedId.CustomerProductId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name = "cartItems")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@IdClass(CustomerProductId.class)
public class CartItems {

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"cartItems"})
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "subproduct_id", nullable = false)
    @JsonIgnoreProperties({"cartItems"})
    private SubProduct subProduct;

    @NotNull
    @NonNull
    @Column(name="quantity")
    private Integer quantity;

}
