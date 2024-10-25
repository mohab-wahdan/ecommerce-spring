package com.example.ecommerce.security.service;

import com.example.ecommerce.models.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class CustomerPrinciple implements UserDetails {

    private Customer customer;

    public CustomerPrinciple(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"  +customer.getAccount().getRoles()));
    }

    @Override
    public String getPassword() {
      //  System.out.println("user password :" +customer.getAccount().getPassword());
        return customer.getAccount().getPassword();
    }

    @Override
    public String getUsername() {
     //   System.out.println("user name :" +customer.getAccount().getUserName());
        return customer.getAccount().getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Customer getCustomer() {
        return customer;
    }
}

