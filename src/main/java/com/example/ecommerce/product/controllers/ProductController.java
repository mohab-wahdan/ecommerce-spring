package com.example.ecommerce.product.controllers;

import com.example.ecommerce.product.dto.ProductJsonAddDTO;
import com.example.ecommerce.product.dto.ProductViewDTO;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.product.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    public ProductController( ProductService productService ) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity< List<ProductViewDTO>> getProducts() {
        return ResponseEntity.ok( productService.getAllProdcutsForView());
    }

    @GetMapping("{id}")
    public ResponseEntity< Product> getProduct(@PathVariable int id) {
        Optional<Product> product = productService.getProductById(id);
        if ( product.isPresent() ) {
            return ResponseEntity.ok( product.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable int id) {
//        productService.deleteProduct(id);
//    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductJsonAddDTO product,
                                             HttpSession session) {
        try {
            productService.createProduct(product);
            session.setAttribute("successMessage", "Product added successfully!");
            return ResponseEntity.ok("Product added successfully!"); // Send a success message
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Failed to add product. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product. Please try again."); // Send an error message
        }
    }

}
