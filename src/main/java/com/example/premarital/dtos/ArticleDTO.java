package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String referencePath;
    private String status;
    private Long approvedUserId;
    private Long therapistId;
    private Long categoryId;
    private Long referenceArticleId;
    @JsonProperty("isActive")
    private Boolean isActive;
}
