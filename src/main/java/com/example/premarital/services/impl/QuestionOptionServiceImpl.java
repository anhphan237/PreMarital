package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.mappers.QuestionOptionMapper;
import com.example.premarital.models.QuestionOption;
import com.example.premarital.models.Wallet;
import com.example.premarital.repositories.QuestionOptionRepository;
import com.example.premarital.services.QuestionOptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class QuestionOptionServiceImpl implements QuestionOptionService {
    private final QuestionOptionRepository questionOptionRepository;
    private final QuestionOptionMapper questionOptionMapper;

    public QuestionOptionServiceImpl(QuestionOptionRepository questionOptionRepository, QuestionOptionMapper questionOptionMapper) {
        this.questionOptionRepository = questionOptionRepository;
        this.questionOptionMapper = questionOptionMapper;
    }

    @Override
    public Page<QuestionOptionDTO> getQuestionOptions(Pageable pageable) {
        Page<QuestionOption> entities = questionOptionRepository.findAll(pageable);
        Page<QuestionOptionDTO> dtoPage = entities.map(new Function<QuestionOption, QuestionOptionDTO>() {

            @Override
            public QuestionOptionDTO apply(QuestionOption questionOption) {
                QuestionOptionDTO dto = questionOptionMapper.toDTO(questionOption);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createQuestionOption(QuestionOptionDTO dto) {
        QuestionOption questionOption = questionOptionMapper.toEntity(dto);
        questionOptionRepository.save(questionOption);
    }

    @Override
    public QuestionOption getQuestionOptionById(Long id) {
        return null;
    }

    @Override
    public boolean deleteQuestionOptionById(Long id) {
        return false;
    }

    @Override
    public boolean updateQuestionOption(Long id, QuestionOptionDTO updatedQuestionOptionDTO) {
        return false;
    }
}
