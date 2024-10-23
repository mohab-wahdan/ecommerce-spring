package com.example.ecommerce.mappers;

import com.example.ecommerce.dtos.CategoryDTO;
import com.example.ecommerce.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDTO toDTO(Category category);
    Category toModel(CategoryDTO categoryDTO);
}