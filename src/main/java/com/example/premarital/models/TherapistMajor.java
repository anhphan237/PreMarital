package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "therapist_majors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TherapistMajor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "therapistMajor")
    private List<Therapist> therapists;
}
