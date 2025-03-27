package com.example.premarital.services;

import com.example.premarital.dtos.QuizCreationDTO;
import com.example.premarital.models.Quiz;
import com.example.premarital.models.Therapist;


public interface QuizService {
    Quiz createQuiz(Therapist therapist, QuizCreationDTO dto);

}
