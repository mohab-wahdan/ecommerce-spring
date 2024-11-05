package com.example.ecommerce.category.controllers;

import com.example.ecommerce.category.dto.SubCategoryDTO;
import com.example.ecommerce.models.SubCategory;
import com.example.ecommerce.category.services.SubCategoryService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public void addSubCategory(
            @RequestParam("category") Integer categoryId,
            @RequestParam("subcategory") String subcategoryName,
            HttpSession session,
            HttpServletResponse response) throws IOException {
        try {
            subCategoryService.createSubCategory(categoryId, subcategoryName);
            session.setAttribute("successMessage", "SubCategory added successfully!");
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Failed to add subcategory. Please try again.");
        }
        response.sendRedirect("/admin/adminDashboard.jsp");
    }


}
