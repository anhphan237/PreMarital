package com.example.premarital.dtos;

import com.example.premarital.models.QuestionOption;
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
public class QuestionChoiceSubmission {

    private Long questionId;

    private String questionText;

    private Long optionId;

    private String optionText;


    public static QuestionChoiceSubmission of(QuestionOption questionOption) {
        return QuestionChoiceSubmission.builder()
                .questionId(questionOption.getQuestion().getQuestionId())
                .questionText(questionOption.getQuestion().getQuestionText())
                .optionId(questionOption.getId())
                .optionText(questionOption.getOptionText())
                .build();
    }

}
