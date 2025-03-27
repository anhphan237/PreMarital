package com.example.premarital.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizAdviceDTO {
    private String adviceText;

    private int fromPoint;

    private int toPoint;
}
