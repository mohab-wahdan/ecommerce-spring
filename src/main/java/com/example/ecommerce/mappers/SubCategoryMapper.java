package com.example.ecommerce.mappers;

import com.example.ecommerce.dtos.SubCategoryDTO;
import com.example.ecommerce.models.SubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {

    SubCategoryMapper INSTANCE = Mappers.getMapper(SubCategoryMapper.class);
    SubCategoryDTO toDTO(SubCategory subCategory);
    SubCategory toModel(SubCategoryDTO subCategoryDTO);
}
