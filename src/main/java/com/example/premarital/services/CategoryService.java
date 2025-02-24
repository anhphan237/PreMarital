package com.example.premarital.services;

import com.example.premarital.dtos.CategoryDTO;
import com.example.premarital.models.Category;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;

public interface CategoryService{
    PagingResult<CategoryDTO> getCategories(PaginationRequest request);
    CategoryDTO createCategory(CategoryDTO dto);
    Category getCategoryById(Long id);
    boolean deleteCategoryById(Long id);
    boolean updateCategory(Long id, CategoryDTO updatedCategoryDTO);
}
