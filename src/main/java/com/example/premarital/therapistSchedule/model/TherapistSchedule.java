package com.example.premarital.therapistSchedule.model;

import com.example.premarital.consultationBooking.model.ConsultationBooking;
import com.example.premarital.therapist.model.Therapist;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "therapist_schedules")
public class TherapistSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "therapist_id", nullable = false)
    private Therapist therapist;

    @OneToOne(mappedBy = "therapistSchedule")
    private ConsultationBooking consultationBooking;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date availableDate;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean isBooked;

    public TherapistSchedule() {
    }

    public TherapistSchedule(Long id, Therapist therapist, Date availableDate, LocalDateTime startTime, LocalDateTime endTime, boolean isBooked) {
        this.id = id;
        this.therapist = therapist;
        this.availableDate = availableDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = isBooked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public Date getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
