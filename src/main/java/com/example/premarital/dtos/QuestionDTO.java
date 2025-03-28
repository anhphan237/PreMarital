package com.example.premarital.dtos;

import com.example.premarital.models.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {
    private Long id;
    private String questionText;
    private Question.QuestionGender forGender;
    private List<QuestionOptionDTO> options;

    public static QuestionDTO of(Question question) {
        return QuestionDTO.builder()
                .id(question.getQuestionId())
                .questionText(question.getQuestionText())
                .forGender(question.getForGender())
                .options(question.getQuestionOption().stream().map(QuestionOptionDTO::of).toList())
                .build();
    }
}
