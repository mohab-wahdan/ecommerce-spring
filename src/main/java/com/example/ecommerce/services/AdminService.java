package com.example.ecommerce.services;

import com.example.ecommerce.models.Admin;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean login(String username, String password) {
        Optional<Admin> admin = adminRepository.
                findByAccount_UserNameAndAccount_Password(username, password);
        return admin.isPresent();
    }
}
