package com.example.premarital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Long id;
    private String questionText;
    private Date createdAt;
    private Date updatedAt;
    private String forGender;
}
