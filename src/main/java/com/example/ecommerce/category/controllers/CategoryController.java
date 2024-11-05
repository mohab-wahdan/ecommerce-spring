package com.example.ecommerce.category.controllers;

import com.example.ecommerce.category.dto.CategoryDTO;
import com.example.ecommerce.category.services.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryDTO> getCategory() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable int id) {
        return categoryService.findCategoryByIdDto(id);
    }

    @PostMapping
    public void addCategory(
            @RequestParam("categoryname") String categoryName,
            HttpSession session,
            HttpServletResponse response) throws IOException {

        try {
            categoryService.createCategory(categoryName);
            session.setAttribute("successMessage", "Category added successfully!");
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Failed to add category. Please try again.");
        }
        response.sendRedirect("/admin/adminDashboard.jsp");
    }

}
