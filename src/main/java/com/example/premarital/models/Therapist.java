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

    @OneToMany(mappedBy = "therapist", fetch = FetchType.LAZY)
    private List<TherapistMajor> therapistMajor;

    @OneToMany(mappedBy = "therapist", fetch = FetchType.LAZY)
    private List<Article> articles;

    private String bio;
    private String therapistCertificationName;
    private String certificationIssuedBy;
    private String certificationIssueDate;
    private String certificationExpirationDate;

    public Therapist() {
    }

    public Therapist(Long id, User user, List<TherapistMajor> therapistMajor, String bio, String therapistCertificationName, String certificationIssuedBy, String certificationIssueDate, String certificationExpirationDate) {
        this.id = id;
        this.user = user;
        this.therapistMajor = therapistMajor;
        this.bio = bio;
        this.therapistCertificationName = therapistCertificationName;
        this.certificationIssuedBy = certificationIssuedBy;
        this.certificationIssueDate = certificationIssueDate;
        this.certificationExpirationDate = certificationExpirationDate;
    }

    public List<TherapistMajor> getTherapistMajor() {
        return therapistMajor;
    }

    public void setTherapistMajor(List<TherapistMajor> therapistMajor) {
        this.therapistMajor = therapistMajor;
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

