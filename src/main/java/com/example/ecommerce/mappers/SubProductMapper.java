package com.example.ecommerce.mappers;



import com.example.ecommerce.dtos.SubProductDTO;
import com.example.ecommerce.dtos.SubProductForAdminDTO;
import com.example.ecommerce.dtos.SubProductJsonAddDTO;
import com.example.ecommerce.enums.Color;
import com.example.ecommerce.enums.Size;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.SubProduct;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.repositories.SubProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class SubProductMapper implements Serializable {

    @Autowired
    private static ProductRepository productRepository;

    public static SubProductDTO convertEntityToDTO(SubProduct subProduct) {
        if (subProduct == null) {
            return null;
        }

        SubProductDTO dto = new SubProductDTO();
        dto.setId(subProduct.getId());
        dto.setProductName(subProduct.getProduct().getName());
        dto.setPrice(subProduct.getPrice());
        dto.setImageURL(subProduct.getImageURL());
        dto.setColor(subProduct.getColor().name());
        dto.setSize(subProduct.getSize().name());
        dto.setStock(subProduct.getStock());
        dto.setDescription(subProduct.getProduct().getDescription());
        dto.setIsDeleted(subProduct.getIsDeleted());
        dto.setIsNewArrival(subProduct.getIsNewArrival());
        dto.setSubCategoryName(subProduct.getProduct().getSubCategory().getName());

        return dto;
    }

    public static SubProductForAdminDTO convertEntityToSubProdcutAdminDTO(SubProduct subProduct) {
        if (subProduct == null) {
            return null;
        }

        SubProductForAdminDTO dto = new SubProductForAdminDTO();
        dto.setId(subProduct.getId());
        dto.setProductName(subProduct.getProduct().getName());
        dto.setPrice(subProduct.getPrice());
        dto.setImageURL(subProduct.getImageURL());
        dto.setColor(subProduct.getColor().name());
        dto.setSize(subProduct.getSize().name());
        dto.setStock(subProduct.getStock());

        return dto;
    }
    public static List<SubProductDTO> fromSubProductEntityToSubProductViewDTO(Optional<List<SubProduct>> subProductsOptional) {
        if (subProductsOptional.isEmpty()) {
            return null;
        }
        List<SubProductDTO> subProductDTOList = new ArrayList<>();

        if (subProductsOptional.isPresent()) {
            List<SubProduct> subProductsList = subProductsOptional.get();

            for (SubProduct subProduct : subProductsList) {
                SubProductDTO dto = SubProductMapper.convertEntityToDTO(subProduct);
                subProductDTOList.add(dto);
            }
        }

        return subProductDTOList;
    }
    public static List<SubProductForAdminDTO> fromSubProductEntityToSubProductForAdminDTO(Optional<List<SubProduct>> subProductsOptional) {
        if (subProductsOptional.isEmpty()) {
            return null;
        }
        List<SubProductForAdminDTO> subProductDTOList = new ArrayList<>();

        if (subProductsOptional.isPresent()) {
            List<SubProduct> subProductsList = subProductsOptional.get();

            for (SubProduct subProduct : subProductsList) {
                SubProductForAdminDTO dto = SubProductMapper.convertEntityToSubProdcutAdminDTO(subProduct);
                subProductDTOList.add(dto);
            }
        }

        return subProductDTOList;
    }

    public static SubProduct converSubProductDTOToEntity(SubProductDTO subProductDTO,String mainProductId) {
        Product product = productRepository.findById(Integer.valueOf(mainProductId)).get();
        SubProduct subProduct = new SubProduct();
        subProduct.setSize(Size.valueOf(subProductDTO.getSize()));
        subProduct.setStock(subProductDTO.getStock());
        subProduct.setColor(Color.valueOf(subProductDTO.getColor()));
        subProduct.setPrice(subProductDTO.getPrice());
        subProduct.setProduct(product);
        subProduct.setImageURL(subProductDTO.getImageURL());
        return subProduct;
    }
//    public static SubProduct converSubProductJsonAddDTOToEntity(SubProductJsonAddDTO subProductDTO, Product product) {
//        SubProduct subProduct = new SubProduct();
//        subProduct.setSize(Size.valueOf(subProductDTO.getSize()));
//        subProduct.setStock(subProductDTO.getStock());
//        subProduct.setColor(Color.valueOf(subProductDTO.getColor()));
//        subProduct.setPrice(subProductDTO.getPrice());
//        subProduct.setProduct(product);
//        subProduct.setImageURL(subProductDTO.getImageURL());
//        return subProduct;
//    }

    public static SubProduct converSubProductNewToEntity(SubProductDTO subProductDTO, Product product) {
        SubProduct subProduct = new SubProduct();
        subProduct.setSize(Size.valueOf(subProductDTO.getSize()));
        subProduct.setStock(subProductDTO.getStock());
        subProduct.setColor(Color.valueOf(subProductDTO.getColor()));
        subProduct.setPrice(subProductDTO.getPrice());
        subProduct.setProduct(product);
        subProduct.setImageURL(subProductDTO.getImageURL());
        return subProduct;
    }

}
