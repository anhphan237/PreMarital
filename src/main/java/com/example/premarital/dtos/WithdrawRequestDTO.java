package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class WithdrawRequestDTO {
    private Long id;
    private Long userId;
    private Long requestAmount;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate requestDate;
    private boolean isApproved;
    private Long approvedBy;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate approvedDate;
    private Long transactionId;

    public WithdrawRequestDTO() {
    }

    public WithdrawRequestDTO(Long id, Long userId, Long requestAmount, LocalDate requestDate, boolean isApproved, Long approvedBy, LocalDate approvedDate, Long transactionId) {
        this.id = id;
        this.userId = userId;
        this.requestAmount = requestAmount;
        this.requestDate = requestDate;
        this.isApproved = isApproved;
        this.approvedBy = approvedBy;
        this.approvedDate = approvedDate;
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(Long requestAmount) {
        this.requestAmount = requestAmount;
    }


    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public Long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
    }
}
