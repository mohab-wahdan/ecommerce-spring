package com.example.ecommerce.category.services;

import com.example.ecommerce.category.dto.CategoryDTO;
import com.example.ecommerce.category.mappers.CategoryMapper;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
