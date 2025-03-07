package com.example.premarital.services.impl;

import com.example.premarital.dtos.QuizUserAdviceDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.mappers.QuizUserAdviceMapper;
import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.models.Wallet;
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
    public QuizUserAdviceDTO getQuizUserAdviceById(Long id) {
        return quizUserAdviceMapper.toDTO(quizUserAdviceRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteQuizUserAdviceById(Long id) {
        return quizUserAdviceRepository.findById(id).map(quizUserAdvice -> {
            quizUserAdvice.setIsActive(false);
            quizUserAdviceRepository.save(quizUserAdvice);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateQuizUserAdvice(Long id, QuizUserAdviceDTO updatedQuizUserAdviceDTO) {
        return quizUserAdviceRepository.findById(id).map(quizUserAdvice -> {
            QuizUserAdvice updatedQuizUserAdvice = quizUserAdviceMapper.toEntityWithId(id, updatedQuizUserAdviceDTO);
            quizUserAdviceRepository.save(updatedQuizUserAdvice);
            return true;
        }).orElse(false);
    }
}
