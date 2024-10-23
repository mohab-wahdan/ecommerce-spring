package com.example.ecommerce.repositories;

import com.example.ecommerce.models.Category;
import com.example.ecommerce.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    List<SubCategory> findByCategory(Category category);

    @Query("SELECT all FROM SubCategory all WHERE all.category.id != 3")
    Optional<List<SubCategory>> findAllExceptAccessories();
}
