package com.example.ecommerce.category.mappers;

import com.example.ecommerce.category.dto.CategoryDTO;
import com.example.ecommerce.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDTO toDTO(Category category);
    Category toModel(CategoryDTO categoryDTO);
}