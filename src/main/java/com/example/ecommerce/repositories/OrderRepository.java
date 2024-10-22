package com.example.ecommerce.repositories;

import com.example.ecommerce.dtos.OrderViewDTO;
import com.example.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Modifying
    @Query(value = "UPDATE orders SET status = ?2 WHERE id = ?1", nativeQuery = true)
    int updateOrderStatus(int id, String status);

    List<OrderViewDTO> findOrdersByCustomerId(Integer id);

}
