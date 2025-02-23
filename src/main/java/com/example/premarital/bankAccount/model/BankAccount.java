package com.example.premarital.bankAccount.model;

import com.example.premarital.wallet.model.Wallet;
import jakarta.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    private String bankName;
    private String bankNumber;

    public BankAccount() {
    }

    public BankAccount(Long id, Wallet wallet, String bankName, String bankNumber) {
        this.id = id;
        this.wallet = wallet;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }
}
