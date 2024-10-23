package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.ProductJsonAddDTO;
import com.example.ecommerce.dtos.ProductViewDTO;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.service.ProductService;
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
    public List<ProductViewDTO> getProducts() {
        return productService.getAllProdcutsForView();
    }
    @GetMapping("{id}")
    public Product getProduct(@PathVariable int id) {
        Optional<Product> product = productService.getProductById(id);
        if ( product.isPresent() ) {
            return product.get();
        }else {
            return null;
        }
    }
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable int id) {
//        productService.deleteProduct(id);
//    }

    @PostMapping
    public void addProduct(@RequestBody ProductJsonAddDTO product) {
        System.out.println(product.toString());
//        productService.createProduct(product);
    }

}
