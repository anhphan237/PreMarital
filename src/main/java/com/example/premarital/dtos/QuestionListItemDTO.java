package com.example.premarital.dtos;

import com.example.premarital.models.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionListItemDTO {
    private Long questionId;
    private String questionText;
    private Question.QuestionGender forGender;
    private int optionsCount;

    public static QuestionListItemDTO of(Question question) {
        return QuestionListItemDTO.builder()
                .questionId(question.getQuestionId())
                .questionText(question.getQuestionText())
                .forGender(question.getForGender())
                .optionsCount(question.getQuestionOption() != null ? question.getQuestionOption().size() : 0)
                .build();
    }
} 