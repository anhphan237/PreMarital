package com.example.premarital.services.impl;

import com.example.premarital.dtos.QuestionOptionDTO;
import com.example.premarital.mappers.QuestionOptionMapper;
import com.example.premarital.models.QuestionOption;
import com.example.premarital.repositories.QuestionOptionRepository;
import com.example.premarital.services.QuestionOptionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {
    private final QuestionOptionRepository questionOptionRepository;
    private final QuestionOptionMapper questionOptionMapper;

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
        questionOptionRepository.save(questionOptionMapper.toEntity(dto));
    }

    @Override
    public QuestionOptionDTO getQuestionOptionById(Long id) {
        return questionOptionMapper.toDTO(questionOptionRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteQuestionOptionById(Long id) {
        return questionOptionRepository.findById(id).map(questionOption -> {
            questionOption.setIsActive(false);
            questionOptionRepository.save(questionOption);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateQuestionOption(Long id, QuestionOptionDTO updatedQuestionOptionDTO) {
        return questionOptionRepository.findById(id).map(questionOption -> {
            QuestionOption updatedQuestionOption = questionOptionMapper.toEntityWithId(id, updatedQuestionOptionDTO);
            questionOptionRepository.save(updatedQuestionOption);
            return true;
        }).orElse(false);
    }
}
