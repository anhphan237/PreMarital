package com.example.premarital.services;

import com.example.premarital.dtos.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService{
    Page<CategoryDTO> getCategories(Pageable pageable);
    void createCategory(CategoryDTO dto);
    CategoryDTO getCategoryById(Long id);
    boolean deleteCategoryById(Long id);
    boolean updateCategory(Long id, CategoryDTO updatedCategoryDTO);
}
