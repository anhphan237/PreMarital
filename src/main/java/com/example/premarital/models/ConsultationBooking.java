package com.example.premarital.models;

import com.example.premarital.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    private String status;
    private Long amount;
    private String meetUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private Boolean isActive;
}
