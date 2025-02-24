package com.example.premarital.category.dto;

public class CategoryDTO {
    private Long id;
    private String name;
    private Long parentCategoryId;
    private String description;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name, Long parentCategoryId, String description) {
        this.id = id;
        this.name = name;
        this.parentCategoryId = parentCategoryId;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
