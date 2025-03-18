package com.example.premarital.services.impl;

import com.example.premarital.dtos.QuizQuestionDTO;
import com.example.premarital.mappers.QuizQuestionMapper;
import com.example.premarital.models.QuizQuestion;
import com.example.premarital.repositories.QuizQuestionRepository;
import com.example.premarital.services.QuizQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class QuizQuestionServiceImpl implements QuizQuestionService {
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizQuestionMapper quizQuestionMapper;

    @Override
    public Page<QuizQuestionDTO> getQuizQuestions(Pageable pageable) {
        Page<QuizQuestion> entities = quizQuestionRepository.findAll(pageable);
        Page<QuizQuestionDTO> dtoPage = entities.map(new Function<QuizQuestion, QuizQuestionDTO>() {

            @Override
            public QuizQuestionDTO apply(QuizQuestion quizQuestion) {
                QuizQuestionDTO dto = quizQuestionMapper.toDTO(quizQuestion);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createQuizQuestion(QuizQuestionDTO dto) {
        QuizQuestion quizQuestion = quizQuestionMapper.toEntity(dto);
        quizQuestionRepository.save(quizQuestion);
    }

    @Override
    public QuizQuestionDTO getQuizQuestionById(Long id) {
        return quizQuestionMapper.toDTO(quizQuestionRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteQuizQuestionById(Long id) {
        return quizQuestionRepository.findById(id).map(quizQuestion -> {
            quizQuestion.setIsActive(false);
            quizQuestionRepository.save(quizQuestion);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateQuizQuestion(Long id, QuizQuestionDTO updatedQuizQuestionDTO) {
        return quizQuestionRepository.findById(id).map(quizQuestion -> {
            QuizQuestion updatedQuizQuestion = quizQuestionMapper.toEntityWithId(id, updatedQuizQuestionDTO);
            quizQuestionRepository.save(updatedQuizQuestion);
            return true;
        }).orElse(false);
    }
}
