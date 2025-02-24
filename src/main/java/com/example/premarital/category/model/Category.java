package com.example.premarital.category.model;

import com.example.premarital.consultationBooking.model.ConsultationBooking;
import com.example.premarital.quizQuestion.model.QuizQuestion;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "category")
    private List<ConsultationBooking> consultationBookings;

    @OneToMany(mappedBy = "category")
    private List<QuizQuestion> quizQuestions;

    private String description;

    public Category() {
    }

    public Category(Long id, String name, List<Category> categories, Category parentCategory, List<ConsultationBooking> consultationBookings, List<QuizQuestion> quizQuestions, String description) {
        this.id = id;
        this.name = name;
        this.categories = categories;
        this.parentCategory = parentCategory;
        this.consultationBookings = consultationBookings;
        this.quizQuestions = quizQuestions;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<ConsultationBooking> getConsultationBookings() {
        return consultationBookings;
    }

    public void setConsultationBookings(List<ConsultationBooking> consultationBookings) {
        this.consultationBookings = consultationBookings;
    }

    public List<QuizQuestion> getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(List<QuizQuestion> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
