package com.example.premarital.dtos;

import java.util.Date;

public class TransactionDTO {
    private Long id;
    private Long walletId;
    private Long amount;
    private String transactionType;
    private Date transactionTime;
    private String transactionStatus;
    private Long balanceBefore;
    private Long transactionFee;
    private Long totalAmount;
    private Long withdrawRequestId;

    public TransactionDTO() {
    }

    public TransactionDTO(Long id, Long walletId, Long amount, String transactionType, Date transactionTime, String transactionStatus, Long balanceBefore, Long transactionFee, Long totalAmount, Long withdrawRequestId) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionTime = transactionTime;
        this.transactionStatus = transactionStatus;
        this.balanceBefore = balanceBefore;
        this.transactionFee = transactionFee;
        this.totalAmount = totalAmount;
        this.withdrawRequestId = withdrawRequestId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
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

    public Long getWithdrawRequestId() {
        return withdrawRequestId;
    }

    public void setWithdrawRequestId(Long withdrawRequestId) {
        this.withdrawRequestId = withdrawRequestId;
    }
}
