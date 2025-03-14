package com.example.premarital.controllers;

import com.example.premarital.dtos.QuizCategoryDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.services.QuizCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/quizCategories")
public class QuizCategoryController {
    private final QuizCategoryService quizCategoryService;

    @GetMapping
    public ResponseEntity<?> getQuizCategories(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        if (page < 1 || size <= 1) {
            return ResponseEntity.badRequest().body("Page number must be >= 1 and size must be > 1");
        }

        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                direction != null ? direction : Sort.Direction.ASC,
                sort != null ? sort : "id"
        );
        Page<QuizCategoryDTO> quizCategories = quizCategoryService.getQuizCategories(pageable);
        return new ResponseEntity<>(quizCategories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createQuizCategory(@RequestBody QuizCategoryDTO quizCategoryDTO){
        quizCategoryService.createQuizCategory(quizCategoryDTO);
        return new ResponseEntity<>("Quiz Category created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizCategoryDTO> findQuizCategoryById(@PathVariable Long id){
        QuizCategoryDTO quizCategory = quizCategoryService.getQuizCategoryById(id);
        return new ResponseEntity<>(quizCategory, quizCategory != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuizCategory(@PathVariable Long id) {
        boolean deleted = quizCategoryService.deleteQuizCategoryById(id);
        return deleted
                ? ResponseEntity.ok("Quiz Category deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz Category not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuizCategory(@PathVariable Long id, @RequestBody QuizCategoryDTO quizCategoryDTO) {
        boolean updated = quizCategoryService.updateQuizCategory(id, quizCategoryDTO);
        return updated
                ? ResponseEntity.ok("Quiz Category updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz Category not found");
    }
}
