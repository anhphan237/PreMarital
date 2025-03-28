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
public class QuestionOptionDTO {
    private Long id;
    private String optionText;
    private int point;

    public static QuestionOptionDTO of(QuestionOption questionOption) {
        return QuestionOptionDTO.builder()
                .id(questionOption.getId())
                .optionText(questionOption.getOptionText())
                .point(questionOption.getPoint())
                .build();
    }
}