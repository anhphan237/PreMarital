package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuizUserAdviceDTO;
import com.example.premarital.dtos.UserQuizHistoryDTO;
import com.example.premarital.mappers.QuizUserAdviceMapper;
import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.models.UserQuizHistory;
import com.example.premarital.repositories.QuizUserAdviceRepository;
import com.example.premarital.dtos.QuizDTO;
import com.example.premarital.services.QuizUserAdviceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class QuizUserAdviceServiceImpl implements QuizUserAdviceService {
    private final QuizUserAdviceRepository quizUserAdviceRepository;
    private final QuizUserAdviceMapper quizUserAdviceMapper;

    public QuizUserAdviceServiceImpl(QuizUserAdviceRepository quizUserAdviceRepository, QuizUserAdviceMapper quizUserAdviceMapper) {
        this.quizUserAdviceRepository = quizUserAdviceRepository;
        this.quizUserAdviceMapper = quizUserAdviceMapper;
    }

    @Override
    public Page<QuizUserAdviceDTO> getQuizUserAdvices(Pageable pageable) {
        Page<QuizUserAdvice> entities = quizUserAdviceRepository.findAll(pageable);
        Page<QuizUserAdviceDTO> dtoPage = entities.map(new Function<QuizUserAdvice, QuizUserAdviceDTO>() {

            @Override
            public QuizUserAdviceDTO apply(QuizUserAdvice quizUserAdvice) {
                QuizUserAdviceDTO dto = quizUserAdviceMapper.toDTO(quizUserAdvice);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createQuizUserAdvice(QuizUserAdviceDTO dto) {
        QuizUserAdvice quizUserAdvice = quizUserAdviceMapper.toEntity(dto);
        quizUserAdviceRepository.save(quizUserAdvice);
    }

    @Override
    public QuizUserAdvice getQuizUserAdviceById(Long id) {
        return null;
    }

    @Override
    public boolean deleteQuizUserAdviceById(Long id) {
        return false;
    }

    @Override
    public boolean updateQuizUserAdvice(Long id, QuizDTO updatedQuizUserAdviceDTO) {
        return false;
    }
}
