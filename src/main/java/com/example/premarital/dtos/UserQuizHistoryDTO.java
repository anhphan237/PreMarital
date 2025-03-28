package com.example.premarital.dtos;

import com.example.premarital.models.UserAnswer;
import com.example.premarital.models.UserQuizHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuizHistoryDTO {
    private String title;

    private String description;

    private String therapistName;

    private String adviceText;

    private LocalDateTime attemptedTime;

    private List<QuestionChoiceSubmission> submissions;

    public static UserQuizHistoryDTO of(UserQuizHistory userQuizHistory) {
        return UserQuizHistoryDTO.builder()
                .title(userQuizHistory.getQuiz().getTitle())
                .description(userQuizHistory.getQuiz().getDescription())
                .therapistName(userQuizHistory.getQuiz().getTherapist().getUser().getFirstName())
                .adviceText(userQuizHistory.getQuizUserAdvice().getAdviceText())
                .attemptedTime(userQuizHistory.getAttemptTime())
                .submissions(
                    userQuizHistory.getUserAnswer().stream()
                        .map(UserAnswer::getQuestionOption)
                        .map(QuestionChoiceSubmission::of)
                        .toList()
                )
                .build();
    }
}
