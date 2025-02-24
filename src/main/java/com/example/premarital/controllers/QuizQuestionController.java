package com.example.premarital.controllers;

import com.example.premarital.services.QuizQuestionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quizQuestions")
public class QuizQuestionController {
    private final QuizQuestionService quizQuestionService;

    public QuizQuestionController(QuizQuestionService quizQuestionService) {
        this.quizQuestionService = quizQuestionService;
    }

}
