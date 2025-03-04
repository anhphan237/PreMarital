package com.example.premarital.models;

import com.example.premarital.models.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Therapists")
public class Therapist {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "therapist_major_id", nullable = false)
    private TherapistMajor therapistMajor;

    @OneToMany(mappedBy = "therapist")
    private List<TherapistSchedule> therapistSchedules;

    @OneToMany(mappedBy = "therapist", fetch = FetchType.LAZY)
    private List<Article> articles;

    private String bio;
    private String therapistCertificationName;
    private String certificationIssuedBy;
    private String certificationIssueDate;
    private String certificationExpirationDate;

    public Therapist() {
    }

    public Therapist(String certificationExpirationDate, String certificationIssueDate, String certificationIssuedBy, String therapistCertificationName, String bio, List<Article> articles, List<TherapistSchedule> therapistSchedules, TherapistMajor therapistMajor, User user, Long id) {
        this.certificationExpirationDate = certificationExpirationDate;
        this.certificationIssueDate = certificationIssueDate;
        this.certificationIssuedBy = certificationIssuedBy;
        this.therapistCertificationName = therapistCertificationName;
        this.bio = bio;
        this.articles = articles;
        this.therapistSchedules = therapistSchedules;
        this.therapistMajor = therapistMajor;
        this.user = user;
        this.id = id;
    }

    public List<TherapistSchedule> getTherapistSchedule() {
        return therapistSchedules;
    }

    public void setTherapistSchedule(List<TherapistSchedule> therapistSchedule) {
        this.therapistSchedules = therapistSchedule;
    }

    public TherapistMajor getTherapistMajor() {
        return therapistMajor;
    }

    public void setTherapistMajor(TherapistMajor therapistMajor) {
        this.therapistMajor = therapistMajor;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTherapistCertificationName() {
        return therapistCertificationName;
    }

    public void setTherapistCertificationName(String therapistCertificationName) {
        this.therapistCertificationName = therapistCertificationName;
    }

    public String getCertificationIssuedBy() {
        return certificationIssuedBy;
    }

    public void setCertificationIssuedBy(String certificationIssuedBy) {
        this.certificationIssuedBy = certificationIssuedBy;
    }

    public String getCertificationIssueDate() {
        return certificationIssueDate;
    }

    public void setCertificationIssueDate(String certificationIssueDate) {
        this.certificationIssueDate = certificationIssueDate;
    }

    public String getCertificationExpirationDate() {
        return certificationExpirationDate;
    }

    public void setCertificationExpirationDate(String certificationExpirationDate) {
        this.certificationExpirationDate = certificationExpirationDate;
    }
}

