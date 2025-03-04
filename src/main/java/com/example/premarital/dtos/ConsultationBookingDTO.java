package com.example.premarital.dtos;

public class ConsultationBookingDTO {
    private Long id;
    private Long therapistScheduleId;
    private Long userId;
    private Long transactionId;
    private String status;
    private Long amount;
    private String meetUrl;
    private Long categoryId;

    public ConsultationBookingDTO() {
    }

    public ConsultationBookingDTO(Long id, Long categoryId, String meetUrl, Long amount, String status, Long transactionId, Long userId, Long therapistScheduleId) {
        this.id = id;
        this.categoryId = categoryId;
        this.meetUrl = meetUrl;
        this.amount = amount;
        this.status = status;
        this.transactionId = transactionId;
        this.userId = userId;
        this.therapistScheduleId = therapistScheduleId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
