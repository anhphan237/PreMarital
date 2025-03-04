package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.CategoryDTO;
import com.example.premarital.mappers.CategoryMapper;
import com.example.premarital.models.Category;
import com.example.premarital.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {
    private final CategoryRepository categoryRepository;

    public CategoryMapperImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }

        return new CategoryDTO(
            category.getId(),
            category.getName(),
            category.getParentCategory() != null ? category.getParentCategory().getId() : null,
            category.getDescription()
        );
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        if (dto.getParentCategoryId() != null) {
            category.setParentCategory(categoryRepository.getReferenceById(dto.getParentCategoryId()));
        }

        return category;
    }
}
