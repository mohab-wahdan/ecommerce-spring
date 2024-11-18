package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.AdminStatsDTO;
import com.example.ecommerce.services.AdminStatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class AdminStatsController {
    AdminStatsService adminStatsService;

    public AdminStatsController(AdminStatsService adminStatsService) {
        this.adminStatsService = adminStatsService;
    }

    @GetMapping()
    AdminStatsDTO getStats() {
        return adminStatsService.getAdminStats();
    }

}
