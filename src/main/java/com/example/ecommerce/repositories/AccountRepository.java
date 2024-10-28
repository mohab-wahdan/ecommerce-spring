package com.example.ecommerce.repositories;

import com.example.ecommerce.models.Account;
import com.example.ecommerce.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account getByUserName(String username);
}
