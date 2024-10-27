package com.example.ecommerce.security;

import com.example.ecommerce.models.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class UserPrinciple implements UserDetails {

    private Account account;

    public UserPrinciple(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"  +account.getRoles()));
    }

    @Override
    public String getPassword() {
      //  System.out.println("user password :" +customer.getAccount().getPassword());
        return account.getPassword();
    }

    @Override
    public String getUsername() {
     //   System.out.println("user name :" +customer.getAccount().getUserName());
        return account.getUserName();
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

    public Account getAccount() {
        return account;
    }
}

