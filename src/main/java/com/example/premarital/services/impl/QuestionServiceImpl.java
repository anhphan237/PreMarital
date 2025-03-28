package com.example.premarital.services.impl;

import com.example.premarital.dtos.QuestionDTO;
import com.example.premarital.dtos.QuestionListItemDTO;
import com.example.premarital.models.Question;
import com.example.premarital.repositories.QuestionRepository;
import com.example.premarital.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public QuestionDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
        return QuestionDTO.of(question);
    }

    @Override
    public List<QuestionListItemDTO> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(QuestionListItemDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionListItemDTO> getQuestionsByGender(Question.QuestionGender gender) {
        return questionRepository.findByGenderIncludingBoth(gender).stream()
                .map(QuestionListItemDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionListItemDTO> searchQuestions(String searchText) {
        return questionRepository.findByQuestionTextContainingIgnoreCase(searchText).stream()
                .map(QuestionListItemDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionListItemDTO> getQuestionsByTherapistId(Long therapistId) {
        return questionRepository.findQuestionsByTherapistId(therapistId).stream()
                .map(QuestionListItemDTO::of)
                .collect(Collectors.toList());
    }
} 