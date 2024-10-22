package com.example.ecommerce.Entities;


import com.example.ecommerce.Entities.EntitiesEmbeddedId.CustomerProductId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name = "cartItems")
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@IdClass(CustomerProductId.class)
public class CartItems {

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "subproduct_id", nullable = false)
    private SubProduct subProduct;

    @NotNull
    @NonNull
    @Column(name="quantity")
    private Integer quantity;

}
