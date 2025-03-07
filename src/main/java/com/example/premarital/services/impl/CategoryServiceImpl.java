package com.example.premarital.services.impl;

import com.example.premarital.dtos.CategoryDTO;
import com.example.premarital.mappers.CategoryMapper;
import com.example.premarital.models.Category;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.repositories.CategoryRepository;
import com.example.premarital.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Page<CategoryDTO> getCategories(Pageable pageable) {
        Page<Category> entities = categoryRepository.findAll(pageable);
        Page<CategoryDTO> dtoPage = entities.map(new Function<Category, CategoryDTO>() {
            @Override
            public CategoryDTO apply(Category category) {
                CategoryDTO dto = categoryMapper.toDTO(category);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createCategory(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        categoryRepository.save(category);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryMapper.toDTO(categoryRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteCategoryById(Long id) {
        return categoryRepository.findById(id).map(category -> {
            category.setIsActive(false);
            categoryRepository.save(category);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateCategory(Long id, CategoryDTO updatedCategoryDTO) {
        return categoryRepository.findById(id).map(category -> {
            Category updatedCategory = categoryMapper.toEntityWithId(id, updatedCategoryDTO);
            categoryRepository.save(updatedCategory);
            return true;
        }).orElse(false);
    }
}
