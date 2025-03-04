package com.example.premarital.dtos;

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
}
