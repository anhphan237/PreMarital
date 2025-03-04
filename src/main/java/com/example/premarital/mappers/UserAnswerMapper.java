package com.example.premarital.mappers;

import com.example.premarital.dtos.UserAnswerDTO;
import com.example.premarital.models.UserAnswer;

public interface UserAnswerMapper {
    UserAnswerDTO toDTO(UserAnswer userAnswer);
    UserAnswer toEntity(UserAnswerDTO userAnswerDTO);
}
