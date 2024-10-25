package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.SubCategoryDTO;
import com.example.ecommerce.models.SubCategory;
import com.example.ecommerce.services.SubCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping()
    public List<SubCategoryDTO> getSubCategory() {
        return subCategoryService.getAllSubCategories();
    }
    @GetMapping("/{id}")
    public SubCategory getSubCategoryById(@PathVariable int id) {
        return subCategoryService.findSubCategoryById(id);
    }

    @PostMapping
    public void createSubCategory(@RequestBody SubCategoryDTO subCategoryDTO) {
        subCategoryService.createSubCategory(subCategoryDTO.getCategoryID(), subCategoryDTO.getName());
    }


}
