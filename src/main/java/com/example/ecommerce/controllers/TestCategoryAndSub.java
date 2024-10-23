package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.CategoryDTO;
import com.example.ecommerce.dtos.SubCategoryDTO;
import com.example.ecommerce.models.SubCategory;
import com.example.ecommerce.services.CategoryService;
import com.example.ecommerce.services.SubCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/cat")
public class TestCategoryAndSub {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    public TestCategoryAndSub(CategoryService categoryService, SubCategoryService subCategoryService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    @GetMapping()
    public List<CategoryDTO> getCategory() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/sub")
    public List<SubCategoryDTO> getSubCategory() {
        return subCategoryService.getAllSubCategories();
    }
    @GetMapping("/sub/{id}")
    public SubCategory getSubCategoryById(@PathVariable int id) {
        return subCategoryService.findSubCategoryById(id);
    }
}
