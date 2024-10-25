package com.example.ecommerce.services;

import com.example.ecommerce.dtos.CategoryDTO;
import com.example.ecommerce.mappers.CategoryMapper;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }


    public  List<CategoryDTO> getAllCategories (){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDTO).collect(Collectors.toList());
    }

    public void createCategory(String categoryName){
        Category category = new Category();
        category.setName(categoryName);
        categoryRepository.save(category);
    }
    public Category findCategoryById(int id){
        return categoryRepository.findById(id).orElse(new Category());
    }
    public CategoryDTO findCategoryByIdDto(int id){
        return categoryMapper.toDTO(categoryRepository.findById(id).orElse(new Category()));
    }

}
