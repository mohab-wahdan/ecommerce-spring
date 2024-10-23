package com.example.ecommerce.repositories;

import com.example.ecommerce.models.Account;
import com.example.ecommerce.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByAccount_UserNameAndAccount_Password(String userName, String password);

    Optional<Customer> findById(Integer customerId);
}
