package com.example.ecommerce.models;


import com.example.ecommerce.enums.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "sub_product")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class SubProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NonNull
    @NotNull
    private Size size;

    @NonNull
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @NonNull
    @NotNull
    private Color color;
    @NonNull
    @NotNull
    private BigDecimal price;

    @Column(name="imageurl")
    private String imageURL;

    @Column(name="is_deleted")
    private Boolean isDeleted = false;

    @Column(name="is_new_arrival")
    private Boolean isNewArrival = false;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}