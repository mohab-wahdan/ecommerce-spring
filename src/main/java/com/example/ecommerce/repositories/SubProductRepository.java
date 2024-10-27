package com.example.ecommerce.repositories;

import com.example.ecommerce.models.SubProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SubProductRepository extends JpaRepository<SubProduct, Integer> ,CustomSubProductRepository {
    @Query("select sub from SubProduct sub where sub.product.id=?1")
    List<SubProduct> findSubProductsByProductID(Integer productId);

    @Query("select sub from SubProduct sub where sub.product.subCategory.category.id=?1")
    List<SubProduct> findSubProductsByCategory(int categoryId) ;

    @Query("select sub from SubProduct sub where sub.product.subCategory.name=?1")
    List<SubProduct> findBySubCategoryName(String subCategoryName) ;

    @Query("select sub from SubProduct sub where sub.product.subCategory.id=?1")
    List<SubProduct> findBySubCategoryId(Integer subCategoryId) ;

    List<SubProduct> findAllByIsDeletedFalse();


    List<SubProduct> findAllBy();

    @Query("select sub.imageURL from SubProduct sub where sub.id=?1")
    String findByImageURL(Integer id);


@Query("select count(c) from SubProduct c")
Long countSubProducts();

    List<SubProduct> findByIsDeletedFalse();

    Optional<SubProduct> findById(Integer subProductId);

}
