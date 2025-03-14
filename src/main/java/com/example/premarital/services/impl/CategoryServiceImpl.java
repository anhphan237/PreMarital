package com.example.premarital.services.impl;

import com.example.premarital.dtos.CategoryDTO;
import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.mappers.CategoryMapper;
import com.example.premarital.models.Category;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.repositories.CategoryRepository;
import com.example.premarital.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public Page<CategoryDTO> getCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findCategoriesByIsActiveTrue(pageable);
        if (categories.isEmpty()) {
            logger.warn("No categories found in the system");
        }
        return categories.map(categoryMapper::toDTO);
    }

    @Override
    @Transactional
    public void createCategory(CategoryDTO dto) {
        if (dto.getId() != null) {
            throw new InvalidDataException("ID must be null when create");
        }

        if (dto.getParentCategoryId() != null && !categoryRepository.existsById(dto.getParentCategoryId())) {
            throw new InvalidDataException("Invalid parent category ID: " + dto.getParentCategoryId());
        }

        try {
            Category category = categoryMapper.toEntity(dto);
            categoryRepository.save(category);
            logger.info("Category created successfully with ID: {}", category.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while creating category: {}", e.getMessage(), e);
            throw new InvalidDataException("Invalid category data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating category: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create category", e);
        }
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Category with ID " + id + " not found"));
    }

    @Override
    @Transactional
    public boolean deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with ID " + id + " not found"));

        if (!category.getIsActive()) {
            logger.warn("Category with ID {} is already inactive", id);
            return false;
        }

        try {
            category.setIsActive(false);
            categoryRepository.save(category);
            logger.info("Category with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating category with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate category", e);
        }
    }

    @Override
    @Transactional
    public boolean updateCategory(Long id, CategoryDTO dto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with ID " + id + " not found"));

        if (dto.getParentCategoryId() != null && !categoryRepository.existsById(dto.getParentCategoryId())) {
            throw new InvalidDataException("Invalid parent category ID: " + dto.getParentCategoryId());
        }

        try {
            Category updatedCategory = categoryMapper.toEntityWithId(id, dto);
            categoryRepository.save(updatedCategory);
            logger.info("Category with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating category with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating category with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update category", e);
        }
    }
}
