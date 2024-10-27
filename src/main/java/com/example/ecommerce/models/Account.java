package com.example.ecommerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Setter
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor
public class Account {
    @NonNull
    @NotEmpty
    @Column(name = "user_name",unique = true)
    private String userName;
    @NonNull
    @NotEmpty
    private String password;

    private String roles;
}
