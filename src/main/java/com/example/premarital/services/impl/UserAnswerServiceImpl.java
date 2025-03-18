package com.example.premarital.services.impl;

import com.example.premarital.dtos.UserAnswerDTO;
import com.example.premarital.mappers.UserAnswerMapper;
import com.example.premarital.models.UserAnswer;
import com.example.premarital.repositories.UserAnswerRepository;
import com.example.premarital.services.UserAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class UserAnswerServiceImpl implements UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;
    private final UserAnswerMapper userAnswerMapper;

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
    public UserAnswerDTO getUserAnswerById(Long id) {
        return userAnswerMapper.toDTO(userAnswerRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteUserAnswerById(Long id) {
        return userAnswerRepository.findById(id).map(userAnswer -> {
            userAnswer.setIsActive(false);
            userAnswerRepository.save(userAnswer);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateUserAnswer(Long id, UserAnswerDTO updatedUserAnswerDTO) {
        return userAnswerRepository.findById(id).map(userAnswer -> {
            UserAnswer updatedUserAnswer = userAnswerMapper.toEntityWithId(id, updatedUserAnswerDTO);
            userAnswerRepository.save(updatedUserAnswer);
            return true;
        }).orElse(false);
    }
}
