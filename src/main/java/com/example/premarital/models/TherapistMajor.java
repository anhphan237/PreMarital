package com.example.premarital.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "therapist_majors")
public class TherapistMajor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "therapistMajor")
    private List<Therapist> therapists;

    public TherapistMajor() {
    }

    public TherapistMajor(Long id, String name, List<Therapist> therapists) {
        this.id = id;
        this.name = name;
        this.therapists = therapists;
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

    public List<Therapist> getTherapists() {
        return therapists;
    }

    public void setTherapists(List<Therapist> therapists) {
        this.therapists = therapists;
    }
}
