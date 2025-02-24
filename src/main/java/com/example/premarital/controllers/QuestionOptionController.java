package com.example.premarital.controllers;

import com.example.premarital.services.QuestionOptionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questionOptions")
public class QuestionOptionController {
    private final QuestionOptionService questionOptionService;

    public QuestionOptionController(QuestionOptionService questionOptionService) {
        this.questionOptionService = questionOptionService;
    }
}
