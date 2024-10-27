package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.SubProductDTO;
import com.example.ecommerce.dtos.SubProductFilterDTO;
import com.example.ecommerce.dtos.SubProductForAdminDTO;
import com.example.ecommerce.enums.Color;
import com.example.ecommerce.enums.Gender;
import com.example.ecommerce.enums.Size;
import com.example.ecommerce.services.SubProductService;

import com.example.ecommerce.models.SubProduct;
import jakarta.servlet.http.Part;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
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


    @PostMapping("/filter")
    public ResponseEntity<List<SubProductDTO>> filterSubProducts(@RequestParam(value = "color", required = false) String colorParam,
                                                                 @RequestParam(value = "size", required = false) String size,
                                                                 @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
                                                                 @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
                                                                 @RequestParam(value = "searchkeyword", required = false) String searchkeyword,
                                                                 @RequestParam(value = "gender", required = false) String gender,
                                                                 @RequestParam(value = "category", required = false) String category,
                                                                 @RequestParam(value = "page", defaultValue = "1") String page) {
        SubProductFilterDTO filterDTO = new SubProductFilterDTO();
        if (searchkeyword != null && !searchkeyword.isEmpty()) {
            searchkeyword = searchkeyword.replaceAll("\\s+", "%");
        }
        filterDTO.setSearchKeyword(searchkeyword);
        filterDTO.setMinPrice(minPrice);
        filterDTO.setMaxPrice(maxPrice);
        if (size != null && !size.isEmpty()) {
            try {
                filterDTO.setSize(Size.valueOf(size.toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid size value: " + size);
            }
        }
        if (colorParam != null && !colorParam.isEmpty()) {
            try {
                filterDTO.setColor(Color.valueOf(colorParam.toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid color value: " + colorParam);
            }
        }

        if (gender != null && !gender.isEmpty()) {
            try {
                filterDTO.setGender(Gender.valueOf(gender.toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid gender value: " + gender);
            }
        }

        filterDTO.setPageNumber(Integer.valueOf(page));
        filterDTO.setCategoryName(category);
        System.out.println(filterDTO);
        return ResponseEntity.ok(subProductService.filterSubProducts(filterDTO));

    }


    @PostMapping
    public ResponseEntity<SubProductDTO> createSubProductDTO(
            @RequestParam("color") String colorParam,
            @RequestParam("mainProduct") String mainProductId,
            @RequestParam("size") String size,
            @RequestParam("quantity") int stock,
            @RequestParam("price") BigDecimal price,
            @RequestPart(value = "image", required = false) MultipartFile imagePart) throws IOException {
        SubProductDTO subProductDTO = subProductService.createSubProductDTO(colorParam, mainProductId, size, stock, price, imagePart);
        System.out.println(subProductDTO);
        return ResponseEntity.ok(subProductDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateSubProductDTO(@PathVariable int id,
                                                    @RequestParam("quantity") int stock,
                                                    @RequestParam("price") BigDecimal price,
                                                    @RequestPart(value = "newImage", required = false) MultipartFile  imagePart) {
        try {
            subProductService.updateSubProduct(id, stock, price, imagePart); // Make sure this handles the image file
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSubProduct(@PathVariable int id) {
        subProductService.deleteSubProduct(id);
        return ResponseEntity.ok().build();
    }
}
