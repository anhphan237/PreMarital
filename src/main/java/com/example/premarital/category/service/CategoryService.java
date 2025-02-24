package com.example.premarital.category.service;

import com.example.premarital.category.dto.CategoryDTO;
import com.example.premarital.category.model.Category;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;

public interface CategoryService{
    PagingResult<CategoryDTO> getCategories(PaginationRequest request);
    CategoryDTO createCategory(CategoryDTO dto);
    Category getCategoryById(Long id);
    boolean deleteCategoryById(Long id);
    boolean updateCategory(Long id, CategoryDTO updatedCategoryDTO);
}
