package com.example.premarital.controllers;

import com.example.premarital.dtos.CategoryDTO;
import com.example.premarital.dtos.ConsultationBookingDTO;
import com.example.premarital.dtos.TherapistScheduleDTO;
import com.example.premarital.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Sort.Direction direction
    ){
        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                direction != null ? direction : Sort.Direction.ASC,
                sort != null ? sort : "id"
        );
        Page<CategoryDTO> categoryDTOS = categoryService.getCategories(pageable);
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>("Category created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Long id){
        CategoryDTO category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, category != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        boolean deleted = categoryService.deleteCategoryById(id);
        return deleted
                ? ResponseEntity.ok("Category deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO updatedCategory) {
        boolean updated = categoryService.updateCategory(id, updatedCategory);
        return updated
                ? ResponseEntity.ok("Category updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
    }
}
