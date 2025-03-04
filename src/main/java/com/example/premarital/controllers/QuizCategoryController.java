package com.example.premarital.controllers;

import com.example.premarital.dtos.QuizCategoryDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.services.QuizCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizCategories")
public class QuizCategoryController {
    private final QuizCategoryService quizCategoryService;

    public QuizCategoryController(QuizCategoryService quizCategoryService) {
        this.quizCategoryService = quizCategoryService;
    }

    @GetMapping
    public ResponseEntity<Page<QuizCategoryDTO>> getQuizCategories(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Sort.Direction direction
    ) {
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
}
