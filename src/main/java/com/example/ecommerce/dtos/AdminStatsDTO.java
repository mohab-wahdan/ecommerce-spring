package com.example.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AdminStatsDTO {
    private long totalCategories;
    private long totalProducts;
    private long totalCustomers;
    private long newCustomers; //new customers in last 7 days
    private long progressOrders;
    private long completedOrders;
    private long numOfLowStock;
    private List<String> lowStock;
    private int numOfNewOrders;
}
