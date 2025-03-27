package com.example.premarital.dtos;

import com.example.premarital.models.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private String questionText;
    private Question.QuestionGender forGender;
    private List<QuestionOptionDTO> options;
}
