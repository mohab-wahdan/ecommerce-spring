package com.example.ecommerce.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import com.example.ecommerce.enums.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String description;

    @NonNull
    @NotEmpty
    private String name;

    @Enumerated(EnumType.STRING)
    @NonNull
    @NotNull
    private Gender gender;

    @NonNull
    @Column(name="is_deleted")
    private String isDeleted;


    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;


}
