package com.example.premarital.services;

import com.example.premarital.dtos.QuestionDTO;
import com.example.premarital.dtos.QuestionListItemDTO;
import com.example.premarital.models.Question;

import java.util.List;

public interface QuestionService {
    
    /**
     * Get a question by ID
     * @param id Question ID
     * @return QuestionDTO
     */
    QuestionDTO getQuestionById(Long id);
    
    /**
     * Get all questions
     * @return List of QuestionListItemDTO
     */
    List<QuestionListItemDTO> getAllQuestions();
    
    /**
     * Get questions by gender
     * @param gender Gender filter
     * @return List of QuestionListItemDTO
     */
    List<QuestionListItemDTO> getQuestionsByGender(Question.QuestionGender gender);
    
    /**
     * Get questions by text search
     * @param searchText Text to search for
     * @return List of QuestionListItemDTO
     */
    List<QuestionListItemDTO> searchQuestions(String searchText);
    
    /**
     * Get questions by therapist ID
     * @param therapistId Therapist ID
     * @return List of QuestionListItemDTO
     */
    List<QuestionListItemDTO> getQuestionsByTherapistId(Long therapistId);
} 