package com.example.premarital.therapistMajor.model;

import com.example.premarital.therapist.model.Therapist;
import jakarta.persistence.*;

@Entity
@Table(name = "therapist_majors")
public class TherapistMajor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "therapist_id", nullable = false)
    private Therapist therapist;

    public TherapistMajor() {
    }

    public TherapistMajor(Long id, String name, Therapist therapist) {
        this.id = id;
        this.name = name;
        this.therapist = therapist;
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

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }
}
