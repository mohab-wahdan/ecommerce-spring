package com.example.ecommerce.repositories;

import com.example.ecommerce.models.SubProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubProductRepository extends JpaRepository<SubProduct, Long> {
    Optional<SubProduct> findById(Integer subProductId);
}
