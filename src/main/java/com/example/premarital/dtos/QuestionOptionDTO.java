package com.example.premarital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptionDTO {
    private Long id;
    private Long questionId;
    private String optionText;
    private int point;
}
