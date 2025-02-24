package com.example.premarital.category.service;

import com.example.premarital.category.dto.CategoryDTO;
import com.example.premarital.category.model.Category;
import com.example.premarital.category.repository.CategoryRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PagingResult<CategoryDTO> getCategories(PaginationRequest request) {
        return null;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        return null;
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public boolean deleteCategoryById(Long id) {
        return false;
    }

    @Override
    public boolean updateCategory(Long id, CategoryDTO updatedCategoryDTO) {
        return false;
    }
}
