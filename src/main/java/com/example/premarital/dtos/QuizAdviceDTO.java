package com.example.premarital.dtos;

import com.example.premarital.models.QuizUserAdvice;
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
public class QuizAdviceDTO {

    private Long id;

    private String adviceText;

    private int fromPoint;

    private int toPoint;

    public static QuizAdviceDTO of(QuizUserAdvice quizUserAdvice) {
        return QuizAdviceDTO.builder()
                .id(quizUserAdvice.getQuizUserAdviceId())
                .adviceText(quizUserAdvice.getAdviceText())
                .fromPoint(quizUserAdvice.getFromPoint())
                .toPoint(quizUserAdvice.getToPoint())
                .build();
    }
}
