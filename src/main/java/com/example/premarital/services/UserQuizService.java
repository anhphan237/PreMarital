package com.example.premarital.services;

import com.example.premarital.dtos.QuizSubmissionDTO;
import com.example.premarital.dtos.UserQuizHistoryDTO;

import java.util.List;

public interface UserQuizService {
    UserQuizHistoryDTO submit(String userEmail, QuizSubmissionDTO quizSubmissionDTO);

    List<UserQuizHistoryDTO> histories(String userEmail);
}
