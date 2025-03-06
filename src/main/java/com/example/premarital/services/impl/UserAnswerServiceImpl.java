package com.example.premarital.services.impl;

import com.example.premarital.dtos.UserAnswerDTO;
import com.example.premarital.mappers.UserAnswerMapper;
import com.example.premarital.models.UserAnswer;
import com.example.premarital.repositories.UserAnswerRepository;
import com.example.premarital.services.UserAnswerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;
    private final UserAnswerMapper userAnswerMapper;

    public UserAnswerServiceImpl(UserAnswerRepository userAnswerRepository, UserAnswerMapper userAnswerMapper) {
        this.userAnswerRepository = userAnswerRepository;
        this.userAnswerMapper = userAnswerMapper;
    }

    @Override
    public Page<UserAnswerDTO> getUserAnswers(Pageable pageable) {
        Page<UserAnswer> entities = userAnswerRepository.findAll(pageable);
        Page<UserAnswerDTO> dtoPage = entities.map(new Function<UserAnswer, UserAnswerDTO>() {

            @Override
            public UserAnswerDTO apply(UserAnswer userAnswer) {
                UserAnswerDTO dto = userAnswerMapper.toDTO(userAnswer);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createUserAnswer(UserAnswerDTO dto) {
        UserAnswer userAnswer = userAnswerMapper.toEntity(dto);
        userAnswerRepository.save(userAnswer);
    }

    @Override
    public UserAnswer getUserAnswerById(Long id) {
        return null;
    }

    @Override
    public boolean deleteUserAnswerById(Long id) {
        return false;
    }

    @Override
    public boolean updateUserAnswer(Long id, UserAnswerDTO updatedUserAnswerDTO) {
        return false;
    }
}
