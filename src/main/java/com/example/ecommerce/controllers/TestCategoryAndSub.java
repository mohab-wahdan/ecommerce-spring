package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.CategoryDTO;
import com.example.ecommerce.dtos.SubCategoryDTO;
import com.example.ecommerce.models.SubCategory;
import com.example.ecommerce.services.CategoryService;
import com.example.ecommerce.services.SubCategoryService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
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
    @GetMapping("/subcategories")
    public List<SubCategoryDTO> getSubCategory() {
        return subCategoryService.getAllSubCategories();
    }
    @GetMapping("/subcategories/{id}")
    public SubCategory getSubCategoryById(@PathVariable int id) {
        return subCategoryService.findSubCategoryById(id);
    }
    @PostMapping
    public void addCategory(
            @RequestParam("categoryname") String categoryName,
            HttpSession session,
            HttpServletResponse response) throws IOException {

        try {
            // Assume addCategoryToDatabase is a service method to save the category to the DB
            categoryService.createCategory(categoryName);
            session.setAttribute("successMessage", "Category added successfully!");
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Failed to add category. Please try again.");
        }

        // Redirect to the dashboard
        response.sendRedirect("/admin/adminDashboard.jsp");
    }
}
