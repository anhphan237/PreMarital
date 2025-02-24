package com.example.premarital.controllers;

import com.example.premarital.services.UserAnswerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAnswers")
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    public UserAnswerController(UserAnswerService userAnswerService) {
        this.userAnswerService = userAnswerService;
    }
}
