package com.example.ecommerce.repositories;

import com.example.ecommerce.dtos.SubProductFilterDTO;
import com.example.ecommerce.models.SubProduct;

import java.util.List;

public interface CustomSubProductRepository {
    long countSubProductsByFilters(SubProductFilterDTO filterDTO);
    List<SubProduct> findSubProductsByFilters(SubProductFilterDTO filterDTO) ;
    SubProduct findSubCategoryById(String searchId);
}
