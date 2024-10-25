package com.example.ecommerce.security.service;

import com.example.ecommerce.models.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AdminPrinciple implements UserDetails {
    private Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+admin.getAccount().getRoles()));
    }

    @Override
    public String getPassword() {
        return admin.getAccount().getPassword();
    }

    @Override
    public String getUsername() {

        return admin.getAccount().getUserName();
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

    public Admin getAdmin() {
        return admin;
    }
}
