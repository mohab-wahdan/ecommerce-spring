package com.example.ecommerce.models;

import com.example.ecommerce.enums.Provider;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private boolean enabled;
}
