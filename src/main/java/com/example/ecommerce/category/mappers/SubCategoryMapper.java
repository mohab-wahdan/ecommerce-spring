package com.example.ecommerce.category.mappers;

import com.example.ecommerce.category.dto.SubCategoryDTO;
import com.example.ecommerce.models.SubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {

    SubCategoryMapper INSTANCE = Mappers.getMapper(SubCategoryMapper.class);
    @Mapping(source = "category.id", target = "categoryID")
    SubCategoryDTO toDTO(SubCategory subCategory);
    SubCategory toModel(SubCategoryDTO subCategoryDTO);
}
