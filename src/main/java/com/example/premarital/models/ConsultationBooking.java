package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "consultation_bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "therapist_schedule_id", nullable = false)
    private TherapistSchedule therapistSchedule;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "consultationBooking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    private String status;
    private Long amount;
    private String meetUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "is_active")
    private Boolean isActive;
}
