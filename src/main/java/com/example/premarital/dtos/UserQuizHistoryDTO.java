package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuizHistoryDTO {
    private Long id;
    private Long userId;
    private int quizPoint;
    private Long quizUserAdviceId;
    @JsonProperty("isActive")
    private Boolean isActive;
}
