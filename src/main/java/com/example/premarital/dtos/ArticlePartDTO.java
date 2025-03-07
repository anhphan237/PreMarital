package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePartDTO {
    private Long id;
    private String title;
    private String content;
    private int orderIndex;
    private String imageLink;
    private Long articleId;
    @JsonProperty("isActive")
    private Boolean isActive;
}
