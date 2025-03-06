package com.example.premarital.services;

import com.example.premarital.dtos.CategoryDTO;
import com.example.premarital.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService{
    Page<CategoryDTO> getCategories(Pageable pageable);
    void createCategory(CategoryDTO dto);
    Category getCategoryById(Long id);
    boolean deleteCategoryById(Long id);
    boolean updateCategory(Long id, CategoryDTO updatedCategoryDTO);
}
