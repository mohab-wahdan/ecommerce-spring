package com.example.ecommerce.mappers;


import com.example.ecommerce.dtos.ProductJsonAddDTO;
import com.example.ecommerce.dtos.ProductViewDTO;
import com.example.ecommerce.models.Product;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductMapper {
    public static Product fromProductViewDTOToProductEntity(ProductViewDTO dto) throws ParseException {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());

        return product;
    }

    public static Product fromProductJsonAddDTOToProductEntity(ProductJsonAddDTO dto)  {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setName(dto.getName());
        product.setIsDeleted("No");
        product.setGender(dto.getGender());
        product.setDescription(dto.getDescription());
        return product;
    }
    public static List<ProductViewDTO> fromProductEntityToProductViewDTO(List<Product> productsOptional) {
        if (productsOptional.isEmpty()) {
            return null;
        }
        List<ProductViewDTO> ProductViewDTOList = new ArrayList<>();


            List<Product> productsList = productsOptional;

            for (Product product : productsList) {
                ProductViewDTO dto = ProductViewDTO.fromProduct(product);
                ProductViewDTOList.add(dto);
            }

        return ProductViewDTOList;
    }

}
