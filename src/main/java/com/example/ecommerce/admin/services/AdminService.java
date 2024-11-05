package com.example.ecommerce.admin.services;

import com.example.ecommerce.models.Admin;
import com.example.ecommerce.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    AdminRepository adminRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository,PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean login(String username, String password) {
        Optional<Admin> admin = adminRepository.
                findByAccount_UserNameAndAccount_Password(username, password);
        return admin.isPresent();
    }

    public void registerAdmin(Admin admin) {
        admin.getAccount().setPassword(passwordEncoder.encode(admin.getAccount().getPassword()));
        adminRepository.save(admin);
    }
}
