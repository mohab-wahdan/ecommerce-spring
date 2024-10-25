package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.ProductJsonAddDTO;
import com.example.ecommerce.dtos.ProductViewDTO;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.services.ProductService;
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
    public ResponseEntity< List<ProductViewDTO> > getProducts() {
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
    public ResponseEntity <Void> addProduct(@RequestBody ProductJsonAddDTO product) {
        System.out.println(product.toString());
        try {
            productService.createProduct(product);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }


    }

}
