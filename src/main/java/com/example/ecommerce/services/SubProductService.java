package com.example.ecommerce.services;

import com.example.ecommerce.dtos.*;
import com.example.ecommerce.enums.Color;
import com.example.ecommerce.enums.Gender;
import com.example.ecommerce.enums.Size;
import com.example.ecommerce.mappers.SubProductMapper;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.SubProduct;
import com.example.ecommerce.repositories.SubProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubProductService {
    SubProductRepository subProductRepository;
    ProductService productService;
    public SubProductService(SubProductRepository subProductRepository, ProductService productService) {
        this.subProductRepository = subProductRepository;
        this.productService = productService;
    }
    public List<SubProduct> findAllSubProducts() {
        return subProductRepository.findAllByIsDeletedFalse();
    }
    public List<SubProductDTO> findAllSubProductsDto() {
        List<SubProduct> subProducts = subProductRepository.findAllByIsDeletedFalse();
        return subProducts.stream().map(SubProductMapper::convertEntityToDTO).collect(Collectors.toList());
    }

    public SubProduct findSubProductById(int id) {
        Optional<SubProduct> subProduct = subProductRepository.findById(id);
        if (subProduct.isPresent()) {
            System.out.println(subProduct.get().getPrice());
            System.out.println(subProduct.get().getColor());
            System.out.println(subProduct.get().getStock());
            if (subProduct.get().getIsDeleted()) {
                return null;
            } else {
                return subProduct.get();
            }
        }
        return null;
    }
    public SubProductForAdminDTO findSubProductForAdminById(Integer id) {
        SubProduct subProduct = subProductRepository.findById(id).get();
        return SubProductMapper.convertEntityToSubProdcutAdminDTO(subProduct);
    }

    public void saveSubProduct(SubProduct subProduct) {
        subProductRepository.save(subProduct);
    }
    public void deleteSubproductById(Integer subProductId) {
        SubProduct subProduct=subProductRepository.findById(subProductId).get();
        subProduct.setIsDeleted(true);
        subProductRepository.save(subProduct);
    }

    public List<SubProduct> findBySubCategoryName(String subcategoryName) {
        return subProductRepository.findBySubCategoryName(subcategoryName);
    }

    public SubProductDTO findSubProductByID(int id){
        SubProduct subProduct=subProductRepository.findById(id).get();
        return SubProductMapper.convertEntityToDTO(subProduct);
    }

    public void deleteSubProduct( int id) {
        SubProduct subProduct=subProductRepository.findById(id).get();
        subProduct.setIsDeleted(true);
        subProductRepository.save(subProduct);
    }

    public SubProductForAdminDTO getSubProductById(int subproductId) {
        return SubProductMapper.convertEntityToSubProdcutAdminDTO(
                (subProductRepository.findById(subproductId).get()));
    }


    public List<SubProductDTO> filterSubProducts(FilterRequest request) {
        SubProductFilterDTO filterDTO=validateFilter(request);
        List<SubProduct> subProducts = subProductRepository.findSubProductsByFilters(filterDTO);

        return subProducts.stream()
                .map(SubProductMapper::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    public long countFilteredSubProducts(SubProductFilterDTO filterDTO) {
        return subProductRepository.countSubProductsByFilters(filterDTO);
    }
    public void addSubProduct(SubProductDTO subProductDTO,int mainProductId) {
        Product product=productService.getProductById(mainProductId).get();
        SubProduct subProductEntity = SubProductMapper.converSubProductNewToEntity(subProductDTO,product);
        subProductRepository.save(subProductEntity);
    }

    public SubProductDTO createSubProductDTO(SubProductRequest request ) throws IOException {
        SubProductDTO subProduct = new SubProductDTO();
        subProduct.setProductName(request.getMainProduct());
        subProduct.setStock(request.getQuantity());
        subProduct.setPrice(request.getPrice());
        subProduct.setColor(request.getColor());
        subProduct.setSize(request.getSize());
        subProduct.setIsDeleted(false);
        MultipartFile imagePart=request.getImage();
        if (!imagePart.isEmpty()) {
            String fileName = Paths.get(imagePart.getOriginalFilename()).getFileName().toString();
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
            String uploadDir = "src/main/webapp/uploads/";
            File uploads = new File(uploadDir);
            if (!uploads.exists()) {
                uploads.mkdirs(); // Create the directory if it doesn't exist
            }
            File file = new File(uploads, uniqueFileName);
            Files.copy(imagePart.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            subProduct.setImageURL("uploads/"+ uniqueFileName);
        }
        addSubProduct(subProduct, Integer.parseInt(subProduct.getProductName()));
        return subProduct;
    }

    public void updateSubProduct(int subProductId, int quantity, BigDecimal price, MultipartFile imagePart) {
        String imageUrl = "";

        if (imagePart != null && !imagePart.isEmpty()) {
            try {
                String fileName = Paths.get(imagePart.getOriginalFilename()).getFileName().toString();
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                String uploadDir = "src/main/webapp/uploads/";
                File uploads = new File(uploadDir);
                if (!uploads.exists()) {
                    uploads.mkdirs();
                }
                File file = new File(uploads, uniqueFileName);
                try (InputStream input = imagePart.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                imageUrl = "uploads/" + uniqueFileName;  // Save as relative path for URL access
            } catch (IOException e) {
                throw new RuntimeException("Error in saving image file", e);
            }
        }else{
            imageUrl = subProductRepository.findByImageURL(subProductId);
        }

        SubProduct subProduct = subProductRepository.findById(subProductId).orElseThrow(() -> new RuntimeException("SubProduct not found"));
        subProduct.setPrice(price);
        subProduct.setStock(quantity);
        subProduct.setImageURL(imageUrl);  // Ensure URL is set in the entity
        subProductRepository.save(subProduct);
    }

    public Optional<SubProductDTO> findSubProductDTOById(int id) {
        Optional<SubProduct> subProduct = subProductRepository.findById(id);
        return subProduct.map(this::convertToDTO);
    }

    private SubProductDTO convertToDTO(SubProduct subProduct) {
        SubProductDTO dto = new SubProductDTO();
        dto.setProductName(subProduct.getProduct().getName());
        dto.setId(subProduct.getId());
        dto.setPrice(subProduct.getPrice());
        dto.setImageURL(subProduct.getImageURL());
        dto.setColor(String.valueOf(subProduct.getColor()));
        dto.setSize(String.valueOf(subProduct.getSize()));
        dto.setDescription(subProduct.getProduct().getDescription());
        dto.setStock(subProduct.getStock());
        dto.setSubCategoryName(subProduct.getProduct().getSubCategory().getName());
        return dto;
    }

    public List<SubProductDTO> findSubProductBySubCategoryIdForAdmin(Integer id) {
        List<SubProduct> subProducts = subProductRepository.findBySubCategoryId(id);
        return subProducts.stream().map(SubProductMapper::convertEntityToDTO).collect(Collectors.toList());
    }

    private SubProductFilterDTO validateFilter(FilterRequest request){
        SubProductFilterDTO filterDTO = new SubProductFilterDTO();
        if (request.getSearchkeyword() != null && !request.getSearchkeyword().isEmpty()) {
            request.setSearchkeyword(request.getSearchkeyword().replaceAll("\\s+", "%"));
        }
        filterDTO.setSearchKeyword(request.getSearchkeyword());
        filterDTO.setMinPrice(request.getMinPrice());
        filterDTO.setMaxPrice(request.getMaxPrice());
        if (request.getSize() != null && !request.getSize().isEmpty()) {
            try {
                filterDTO.setSize(Size.valueOf(request.getSize().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid size value: " + request.getSize());
            }
        }
        if (request.getColor() != null && !request.getColor().isEmpty()) {
            try {
                filterDTO.setColor(Color.valueOf(request.getColor().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid color value: " + request.getColor());
            }
        }

        if (request.getGender() != null && !request.getGender().isEmpty()) {
            try {
                filterDTO.setGender(Gender.valueOf(request.getGender().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid gender value: " + request.getGender());
            }
        }

        filterDTO.setPageNumber(Integer.valueOf(request.getPage()));
        filterDTO.setSubCategoryName(request.getCategory());
        System.out.println(filterDTO);
        return filterDTO;
    }
}
