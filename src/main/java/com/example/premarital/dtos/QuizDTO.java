package com.example.premarital.dtos;

import com.example.premarital.models.QuizUserAdvice;
import com.example.premarital.models.Therapist;
import com.example.premarital.models.UserQuizHistory;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {
    private Long id; // Nullable (used for updates)

    @NotNull(message = "Creator ID cannot be null")
    private Long creatorId;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    private String description; // Optional

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @NotBlank(message = "Status cannot be empty")
    private String status;

    @JsonProperty("isActive")
    private Boolean isActive = true; // Default to true

    @NotNull(message = "User quiz history ID cannot be null")
    private Long userQuizHistoryId;
}
