package com.example.premarital.models;

import com.example.premarital.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "Therapists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Therapist {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "therapist_major_id")
    @ToString.Exclude
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
    private Boolean isActive;
}

