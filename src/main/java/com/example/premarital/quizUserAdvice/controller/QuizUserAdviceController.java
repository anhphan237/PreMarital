package com.example.premarital.quizUserAdvice.controller;

import com.example.premarital.quizUserAdvice.service.QuizUserAdviceService;
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
