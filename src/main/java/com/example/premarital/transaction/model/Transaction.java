package com.example.premarital.transaction.model;

import com.example.premarital.consultationBooking.model.ConsultationBooking;
import com.example.premarital.wallet.model.Wallet;
import com.example.premarital.withdrawRequest.model.WithdrawRequest;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    private Long amount;
    private String transactionType;
    private Date transactionTime;
    private String transactionStatus;
    private Long balanceBefore;
    private Long transactionFee;
    private Long totalAmount;

    @OneToOne(mappedBy = "transaction")
    private WithdrawRequest withdrawRequest;

    @OneToOne(mappedBy = "transaction")
    private ConsultationBooking consultationBooking;

    public Transaction() {
    }

    public Transaction(Long id, Wallet wallet, Long amount, String transactionType, Date transactionTime, String transactionStatus, Long balanceBefore, Long transactionFee, Long totalAmount, WithdrawRequest withdrawRequest) {
        this.id = id;
        this.wallet = wallet;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionTime = transactionTime;
        this.transactionStatus = transactionStatus;
        this.balanceBefore = balanceBefore;
        this.transactionFee = transactionFee;
        this.totalAmount = totalAmount;
        this.withdrawRequest = withdrawRequest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Long getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(Long balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    public Long getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(Long transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public WithdrawRequest getWithdrawRequest() {
        return withdrawRequest;
    }

    public void setWithdrawRequest(WithdrawRequest withdrawRequest) {
        this.withdrawRequest = withdrawRequest;
    }
}
