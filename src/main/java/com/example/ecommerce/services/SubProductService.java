package com.example.ecommerce.services;

import com.example.ecommerce.dtos.SubProductDTO;
import com.example.ecommerce.dtos.SubProductFilterDTO;
import com.example.ecommerce.dtos.SubProductForAdminDTO;
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


    public List<SubProductDTO> filterSubProducts(SubProductFilterDTO filterDTO) {
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

    public SubProductDTO createSubProductDTO(String colorParam, String mainProductId, String size, int stock, BigDecimal price, MultipartFile imagePart) throws IOException {
        SubProductDTO subProduct = new SubProductDTO();
//        subProduct.setProductName(mainProductId);
        subProduct.setStock(stock);
        subProduct.setPrice(price);
        subProduct.setColor(colorParam);
        subProduct.setSize(size);
        subProduct.setIsDeleted(false);
        if (!imagePart.isEmpty()) {
            String fileName = Paths.get(imagePart.getOriginalFilename()).getFileName().toString();
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
            String uploadDir = "uploads/"; // Define the directory to store uploaded images
            File uploads = new File(uploadDir);
            if (!uploads.exists()) {
                uploads.mkdirs(); // Create the directory if it doesn't exist
            }
            File file = new File(uploads, uniqueFileName);
            Files.copy(imagePart.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            subProduct.setImageURL(uploadDir + uniqueFileName);
        }
//        addSubProduct(subProduct, Integer.parseInt(subProduct.getProductName()));
        return subProduct;
    }

    public void updateSubProduct(int subProductId,int quantity,BigDecimal price ,MultipartFile imagePart)   {
        String imageUrl ="";
        if (!imagePart.isEmpty()) {
            String fileName = Paths.get(imagePart.getOriginalFilename()).getFileName().toString();
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
            String uploadDir = "uploads/";
            File uploads = new File(uploadDir);
            if (!uploads.exists()) {
                uploads.mkdirs(); // Create the directory if it doesn't exist
            }
            File file = new File(uploads, uniqueFileName);
            try (InputStream input = imagePart.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("error in image input stream");
            }
            imageUrl=uploadDir+uniqueFileName;
        }
        SubProduct subProduct=subProductRepository.findById(subProductId).get();
        subProduct.setPrice(price);
        subProduct.setStock(quantity);
        subProduct.setImageURL(imageUrl);
        subProductRepository.save(subProduct);
    }
    public Optional<SubProductDTO> findSubProductDTOById(int id) {
        Optional<SubProduct> subProduct = subProductRepository.findById(id);
        return subProduct.map(this::convertToDTO);
    }

    private SubProductDTO convertToDTO(SubProduct subProduct) {
        SubProductDTO dto = new SubProductDTO();
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

}
