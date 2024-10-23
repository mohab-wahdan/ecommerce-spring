package com.example.ecommerce.service;

import com.example.ecommerce.dtos.ProductJsonAddDTO;
import com.example.ecommerce.dtos.ProductViewDTO;
import com.example.ecommerce.mappers.ProductMapper;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.SubCategory;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.services.SubCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;
    SubCategoryService subCategoryService;
    public ProductService(ProductRepository productRepository,SubCategoryService subCategoryService) {
        this.productRepository = productRepository;
        this.subCategoryService = subCategoryService;
    }

    public List<ProductViewDTO> getAllProdcutsForView() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.fromProductEntityToProductViewDTO(products);
    }

    public void createProduct(ProductJsonAddDTO jsonProduct) {
        Product product=ProductMapper.fromProductJsonAddDTOToProductEntity(jsonProduct);
       SubCategory subCategory= subCategoryService.findSubCategoryById(jsonProduct.getSubCategoryId());
        product.setSubCategory(subCategory);
        productRepository.save(product);
    }
//    public void deleteProduct(int id) {
//        Product product = productRepository.findById(id).get();
//        product.setIsDeleted("YES");
//        productRepository.save(product);
//    }
    public void updateProduct(Product product) {
        productRepository.save(product);
    }
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

}
