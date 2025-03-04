package com.example.premarital.mappers;

import com.example.premarital.dtos.QuestionDTO;
import com.example.premarital.models.Question;

public interface QuestionMapper {
    QuestionDTO toDTO(Question question);
    Question toEntity(QuestionDTO questionDTO);
}
