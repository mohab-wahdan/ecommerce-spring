package com.example.ecommerce.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "sub_category")
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @NonNull
    @Column(unique = true)
    private String name;

    public SubCategory(Category category, @NonNull String name) {
        this.category = category;
        this.name = name;
    }



}
