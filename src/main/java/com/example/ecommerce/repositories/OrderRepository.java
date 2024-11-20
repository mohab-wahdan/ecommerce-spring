package com.example.ecommerce.repositories;

import com.example.ecommerce.enums.Status;
import com.example.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET status = ?2 WHERE id = ?1", nativeQuery = true)
    int updateOrderStatus(Integer id, String status);

    Order findOrderById(Integer id);

    @Query(value = "SELECT * FROM orders WHERE customer_id = ?1", nativeQuery = true)
    List<Order> findOrdersByCustomerId(Integer id);

    @Query(value = "SELECT * FROM orders WHERE status = ?1", nativeQuery = true)
    List<Order> findOrdersByStatus(String status);

    @Query(value = "SELECT * FROM orders WHERE created_at >= CURRENT_DATE - INTERVAL 5 DAY", nativeQuery = true)
    List<Order> findOrdersFromLastFiveDays();
    long countOrdersByStatus(Status status);
}
