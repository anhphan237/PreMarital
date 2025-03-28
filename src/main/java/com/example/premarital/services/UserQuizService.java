package com.example.premarital.services;

import com.example.premarital.dtos.QuizSubmissionDTO;

public interface UserQuizService {
    void submit(String userEmail, QuizSubmissionDTO quizSubmissionDTO);
}
