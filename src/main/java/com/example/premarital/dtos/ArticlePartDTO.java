package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePartDTO {
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    @PositiveOrZero(message = "Order index must be zero or a positive number")
    private int orderIndex;

    private String imageLink;

    @NotNull(message = "Article ID cannot be null")
    @PositiveOrZero(message = "Article ID must be zero or a positive number")
    private Long articleId;

    @JsonProperty("isActive")
    private Boolean isActive;
}
