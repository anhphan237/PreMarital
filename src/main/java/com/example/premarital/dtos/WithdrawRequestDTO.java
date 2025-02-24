package com.example.premarital.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class WithdrawRequestDTO {
    private Long id;
    private Long userId;
    private Long requestAmount;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date requestDate;
    private boolean isApproved;
    private Long approvedBy;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date approvedDate;

    public WithdrawRequestDTO() {
    }

    public WithdrawRequestDTO(Long id, Long userId, Long requestAmount, Date requestDate, boolean isApproved, Long approvedBy, Date approvedDate) {
        this.id = id;
        this.userId = userId;
        this.requestAmount = requestAmount;
        this.requestDate = requestDate;
        this.isApproved = isApproved;
        this.approvedBy = approvedBy;
        this.approvedDate = approvedDate;
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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
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

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }
}
