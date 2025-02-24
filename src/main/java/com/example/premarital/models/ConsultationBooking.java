package com.example.premarital.models;

import com.example.premarital.models.User;
import jakarta.persistence.*;

@Entity
@Table(name = "consultation_bookings")
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

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String status;
    private Long amount;
    private String meetUrl;

    public ConsultationBooking() {
    }

    public ConsultationBooking(Long id, TherapistSchedule therapistSchedule, User user, Transaction transaction, Category category, String status, Long amount, String meetUrl) {
        this.id = id;
        this.therapistSchedule = therapistSchedule;
        this.user = user;
        this.transaction = transaction;
        this.category = category;
        this.status = status;
        this.amount = amount;
        this.meetUrl = meetUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TherapistSchedule getTherapistSchedule() {
        return therapistSchedule;
    }

    public void setTherapistSchedule(TherapistSchedule therapistSchedule) {
        this.therapistSchedule = therapistSchedule;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getMeetUrl() {
        return meetUrl;
    }

    public void setMeetUrl(String meetUrl) {
        this.meetUrl = meetUrl;
    }
}
