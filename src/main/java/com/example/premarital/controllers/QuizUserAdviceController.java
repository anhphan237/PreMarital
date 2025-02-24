package com.example.premarital.controllers;

import com.example.premarital.services.QuizUserAdviceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quizUserAdvices")
public class QuizUserAdviceController {
    private final QuizUserAdviceService quizUserAdviceService;

    public QuizUserAdviceController(QuizUserAdviceService quizUserAdviceService) {
        this.quizUserAdviceService = quizUserAdviceService;
    }
}
