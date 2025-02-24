package com.example.premarital.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "withdraw_requests")
public class WithdrawRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Long requestAmount;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date requestDate;
    private boolean isApproved;
    private Long approvedBy;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date approvedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    public WithdrawRequest() {
    }

    public WithdrawRequest(Long id, User user, Long requestAmount, Date requestDate, boolean isApproved, Long approvedBy, Date approvedDate) {
        this.id = id;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
