package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {
    private Long id;

    @NotNull(message = "Creator ID cannot be null")
    private Long creatorId;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @NotBlank(message = "Status cannot be empty")
    private String status;

    @JsonProperty("isActive")
    private Boolean isActive = true;

    @NotNull(message = "User quiz history ID cannot be null")
    private Long userQuizHistoryId;
}
