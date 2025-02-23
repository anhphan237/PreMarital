package com.example.premarital.consultationBooking.dto;

import com.example.premarital.therapistSchedule.model.TherapistSchedule;
import com.example.premarital.transaction.model.Transaction;
import com.example.premarital.user.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class ConsultationBookingDTO {
    private Long id;
    private Long therapistScheduleId;
    private Long userId;
    private Long transactionId;
    private String status;
    private Long amount;
    private String meetUrl;

    public ConsultationBookingDTO() {
    }

    public ConsultationBookingDTO(Long id, Long therapistScheduleId, Long userId, Long transactionId, String status, Long amount, String meetUrl) {
        this.id = id;
        this.therapistScheduleId = therapistScheduleId;
        this.userId = userId;
        this.transactionId = transactionId;
        this.status = status;
        this.amount = amount;
        this.meetUrl = meetUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTherapistScheduleId() {
        return therapistScheduleId;
    }

    public void setTherapistScheduleId(Long therapistScheduleId) {
        this.therapistScheduleId = therapistScheduleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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
