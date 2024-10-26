package com.example.ecommerce.repositories;

import com.example.ecommerce.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Optional<Admin> findByAccount_UserNameAndAccount_Password(String user_name, String password);


    Optional<Admin> findByAccount_UserName(String username);
}
