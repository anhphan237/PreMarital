package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    private String referencePath;

    @NotBlank(message = "Status is required")
    private String status;

    @Positive(message = "Approved User ID must be a positive number")
    private Long approvedUserId;

    @NotNull(message = "Therapist ID cannot be null")
    @Positive(message = "Therapist ID must be a positive number")
    private Long therapistId;

    @NotNull(message = "Category ID cannot be null")
    @Positive(message = "Category ID must be a positive number")
    private Long categoryId;

    @Positive(message = "Reference Article ID must be a positive number")
    private Long referenceArticleId;

    @JsonProperty("isActive")
    private Boolean isActive;
}
