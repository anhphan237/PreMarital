package com.example.premarital.quizCategory.controller;

import com.example.premarital.quizCategory.service.QuizCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quizCategories")
public class QuizCategoryController {
    private final QuizCategoryService quizCategoryService;

    public QuizCategoryController(QuizCategoryService quizCategoryService) {
        this.quizCategoryService = quizCategoryService;
    }
}
