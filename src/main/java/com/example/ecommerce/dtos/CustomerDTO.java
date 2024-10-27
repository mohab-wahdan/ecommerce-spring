package com.example.ecommerce.dtos;

import com.example.ecommerce.models.Account;
import com.example.ecommerce.models.Address;
import com.example.ecommerce.models.CartItems;
import com.example.ecommerce.models.Order;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomerDTO implements Serializable {

    private Integer id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private BigDecimal creditLimit;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Email(message = "Invalid email address")
    @NotEmpty
    private String email;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String job;


    @NonNull
    private Address address; // Changed to use Address class

    @NonNull
    private Account account;

    private Set<CartItemsDTO> shoppingCart = new HashSet<>();

    private Set<OrderDTO> orders = new HashSet<>();

}
