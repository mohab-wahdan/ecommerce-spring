package com.example.ecommerce.services;

import com.example.ecommerce.dtos.AdminStatsDTO;
import com.example.ecommerce.dtos.ProductColorDTO;
import com.example.ecommerce.enums.Status;
import com.example.ecommerce.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminStatsService {
    SubProductRepository subProductRepository;
    CategoryRepository categoryRepository;
    CustomerRepository customerRepository;
    OrderRepository orderRepository;

    public AdminStatsService(SubProductRepository subProductRepository, CategoryRepository categoryRepository, CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.subProductRepository = subProductRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public AdminStatsDTO getAdminStats() {
        AdminStatsDTO stats = new AdminStatsDTO();
        int lowStock = 100;

        stats.setTotalCategories(categoryRepository.count());
        stats.setTotalProducts(subProductRepository.count());
        stats.setTotalCustomers(customerRepository.count());
        stats.setNewCustomers(customerRepository.countByOrdersIsNotEmpty());
        stats.setProgressOrders(orderRepository.countOrdersByStatus(Status.PENDING));
        stats.setCompletedOrders(orderRepository.countOrdersByStatus(Status.COMPLETED));
        List<String> lowStockProducts = subProductRepository.findLowStockProducts(lowStock).
                stream().map(ProductColorDTO::toString).toList();
        stats.setNumOfLowStock(lowStockProducts.size());
        stats.setLowStock(lowStockProducts);

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, -5);

        Date newDate = calendar.getTime();
        int newOrders = orderRepository.countOrdersByCreatedAtGreaterThan(currentDate);
        stats.setNumOfNewOrders(newOrders);
        return stats;
    }


}
