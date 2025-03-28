package com.example.premarital.services;

import com.example.premarital.dtos.QuizCreationDTO;
import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.dtos.QuizUpdateDTO;
import com.example.premarital.models.Therapist;

import java.util.List;


public interface QuizService {
    QuizDTO createQuiz(Therapist therapist, QuizCreationDTO dto);

    QuizDTO getQuizById(Long id);

    List<QuizDTO> getAllQuizzes();

    List<QuizDTO> getQuizzesByTherapistEmail(String email);
    
    QuizDTO updateQuiz(Long id, QuizUpdateDTO dto, String therapistEmail);
}
