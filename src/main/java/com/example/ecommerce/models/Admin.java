package com.example.ecommerce.models;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "admin")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Embedded
    private Account account;
}
