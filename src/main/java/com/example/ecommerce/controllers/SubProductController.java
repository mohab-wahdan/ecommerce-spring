package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.*;
import com.example.ecommerce.enums.Color;
import com.example.ecommerce.enums.Gender;
import com.example.ecommerce.enums.Size;
import com.example.ecommerce.services.SubProductService;

import com.example.ecommerce.models.SubProduct;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/subProducts")
public class SubProductController {
    SubProductService subProductService;

    public SubProductController(SubProductService subProductService) {
        this.subProductService = subProductService;
    }

    @GetMapping
    public ResponseEntity<List<SubProductDTO>> getAllSubProducts() {

        return ResponseEntity.ok(subProductService.findAllSubProductsDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubProduct> getSubProduct( @PathVariable int id) {

        SubProduct subProduct = subProductService.findSubProductById(id);
        if (subProduct !=null) {
            return ResponseEntity.ok(subProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/subproductId/{id}")
    public ResponseEntity<SubProductForAdminDTO> getSubProductForAdmin(@PathVariable Integer id) {

        SubProductForAdminDTO subProduct = subProductService.findSubProductForAdminById(id);
        if (subProduct !=null) {
            return ResponseEntity.ok(subProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/subcategoryId/{id}")
    public ResponseEntity<List<SubProductDTO>> getSubProductBySubCategoryIdForAdmin(@PathVariable Integer id) {
        return ResponseEntity.ok(subProductService.findSubProductBySubCategoryIdForAdmin(id));
    }


    @PostMapping("/filter")
    public ResponseEntity filterSubProducts( @ModelAttribute FilterRequest filterRequest ,
                                                                 @RequestParam(value = "page", defaultValue = "1") String page) {
       filterRequest.setPage(page);
        List<SubProductDTO> products=subProductService.filterSubProducts(filterRequest);
        Long total =subProductService.countFilteredSubProducts(filterRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("total", total);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<String> createSubProductDTO(@ModelAttribute SubProductRequest subProductRequest,
                                                      @RequestPart(value = "image", required = false) MultipartFile imagePart,
            HttpSession session) throws IOException {
        try {
            subProductRequest.setImage(imagePart);
            SubProductDTO subProductDTO = subProductService.createSubProductDTO(subProductRequest);
            session.setAttribute("successMessage", "SubProduct added successfully!");
            return ResponseEntity.ok("Product added successfully!"); // Send a success message
        }catch (Exception e){
            session.setAttribute("errorMessage", "Failed to add subproduct. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product. Please try again."); // Send an error message
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateSubProductDTO(@PathVariable int id, @RequestParam("quantity") int stock, @RequestParam("price") BigDecimal price,
                                                    @RequestPart(value = "newImage", required = false) MultipartFile  imagePart, HttpSession session) {
        try {
            subProductService.updateSubProduct(id, stock, price, imagePart); // Make sure this handles the image file
            session.setAttribute("successMessage", "subproduct updated successfully!");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Failed to add subproduct. Please try again.");
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSubProduct(@PathVariable int id) {
        subProductService.deleteSubProduct(id);
        return ResponseEntity.ok().build();
    }
}
