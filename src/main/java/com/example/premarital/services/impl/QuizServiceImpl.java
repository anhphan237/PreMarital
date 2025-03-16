package com.example.premarital.services.impl;

import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.mappers.QuizMapper;
import com.example.premarital.models.Quiz;
import com.example.premarital.services.QuizService;
import com.example.premarital.repositories.QuizRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    public QuizServiceImpl(QuizRepository quizRepository, QuizMapper quizMapper) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
    }

    @Override
    public Page<QuizDTO> getQuizzes(Pageable pageable) {
        Page<Quiz> entities = quizRepository.findAll(pageable);
        Page<QuizDTO> dtoPage = entities.map(new Function<Quiz, QuizDTO>() {

            @Override
            public QuizDTO apply(Quiz quiz) {
                QuizDTO dto = quizMapper.toDTO(quiz);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createQuiz(QuizDTO dto) {
        quizRepository.save(quizMapper.toEntity(dto));
    }

    @Override
    public QuizDTO getQuizById(Long id) {
        return quizMapper.toDTO(quizRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteQuizById(Long id) {
        return quizRepository.findById(id).map(quiz -> {
            quiz.setIsActive(false);
            quizRepository.save(quiz);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateQuiz(Long id, QuizDTO updatedQuizDTO) {
        return quizRepository.findById(id).map(quiz -> {
            Quiz updatedQuiz = quizMapper.toEntityWithId(id, updatedQuizDTO);
            quizRepository.save(updatedQuiz);
            return true;
        }).orElse(false);
    }
}
