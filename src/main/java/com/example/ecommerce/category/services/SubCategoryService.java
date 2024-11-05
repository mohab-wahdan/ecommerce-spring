package com.example.ecommerce.category.services;

import com.example.ecommerce.category.dto.SubCategoryDTO;
import com.example.ecommerce.category.mappers.SubCategoryMapper;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.models.SubCategory;
import com.example.ecommerce.category.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final SubCategoryMapper subCategoryMapper ;
    private final CategoryService categoryService;

    @Autowired
    public SubCategoryService(SubCategoryRepository subCategoryRepository, SubCategoryMapper subCategoryMapper, CategoryService categoryService) {
        this.subCategoryRepository = subCategoryRepository;
        this.subCategoryMapper = subCategoryMapper;
        this.categoryService = categoryService;
    }


//    public List<SubCategoryDTO> getAllSubCategories(){
//        Optional<List<SubCategory>> subCategoryEntity = Optional.of(subCategoryRepository.findAll());
//        return subCategoryEntity.stream()
//                .map(entity -> subCategoryMapper.toDTO((SubCategory) entity)).
//                collect(Collectors.toList());
//    }
    public List<SubCategoryDTO> getAllSubCategories(){
        Optional<List<SubCategory>> subCategoryEntity = Optional.of(subCategoryRepository.findAll());
        List<SubCategory> subCategories = subCategoryEntity.orElse(new ArrayList<>());
        return subCategories.stream()
                .map(subCategoryMapper::toDTO).
                collect(Collectors.toList());
    }

    public void createSubCategory(int categoryId, String subCategoryName) {
        Category category = categoryService.findCategoryById(categoryId);

        SubCategory subCategory = new SubCategory();
        subCategory.setCategory(category);
        subCategory.setName(subCategoryName);
        subCategoryRepository.save(subCategory);
    }

    public SubCategory findSubCategoryById (int id) {
        return subCategoryRepository.findById(id).orElse(new SubCategory());
    }
}
