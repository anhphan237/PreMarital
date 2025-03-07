package com.example.premarital.mappers;

import com.example.premarital.dtos.CategoryDTO;
import com.example.premarital.models.Category;

public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO dto);
    Category toEntityWithId(Long id, CategoryDTO dto);
}
