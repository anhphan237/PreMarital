package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDTO {
    private Long id;

    @NotNull(message = "User quiz history ID cannot be null")
    private Long userQuizHistoryId;

    @NotNull(message = "Question option ID cannot be null")
    private Long questionOptionId;

    @JsonProperty("isActive")
    private Boolean isActive = true;
}
