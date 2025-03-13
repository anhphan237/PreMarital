package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;

    @NotBlank(message = "Category name cannot be empty")
    @Size(max = 100, message = "Category name must be at most 100 characters")
    private String name;

    @Min(value = 1, message = "Parent category ID must be a positive number")
    private Long parentCategoryId;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    @NotNull(message = "isActive cannot be null")
    @JsonProperty("isActive")
    private Boolean isActive;
}
