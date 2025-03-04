package com.example.premarital.services.impl;

import com.example.premarital.dtos.QuestionDTO;
import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.mappers.QuestionMapper;
import com.example.premarital.models.Question;
import com.example.premarital.models.QuestionOption;
import com.example.premarital.repositories.QuestionRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.services.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public Page<QuestionDTO> getQuestions(Pageable pageable) {
        Page<Question> entities = questionRepository.findAll(pageable);
        Page<QuestionDTO> dtoPage = entities.map(new Function<Question, QuestionDTO>() {

            @Override
            public QuestionDTO apply(Question question) {
                QuestionDTO dto = questionMapper.toDTO(question);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createQuestion(QuestionDTO dto) {
        Question question = questionMapper.toEntity(dto);
        questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return null;
    }

    @Override
    public boolean deleteQuestionById(Long id) {
        return false;
    }

    @Override
    public boolean updateQuestion(Long id, QuestionDTO updatedQuestionDTO) {
        return false;
    }
}
