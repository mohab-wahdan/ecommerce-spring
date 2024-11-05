package com.example.ecommerce.product.repository;

import com.example.ecommerce.product.dto.SubProductFilterDTO;
import com.example.ecommerce.models.SubProduct;

import java.util.List;

public interface CustomSubProductRepository {
    long countSubProductsByFilters(SubProductFilterDTO filterDTO);
    List<SubProduct> findSubProductsByFilters(SubProductFilterDTO filterDTO) ;
    SubProduct findSubCategoryById(String searchId);
}
