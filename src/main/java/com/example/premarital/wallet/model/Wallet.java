package com.example.premarital.wallet.model;

import com.example.premarital.bankAccount.model.BankAccount;
import com.example.premarital.transaction.model.Transaction;
import com.example.premarital.user.model.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private Long balance;

    @OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
    private List<Transaction> transactions;
    public Wallet() {
    }

    public Wallet(Long id, User user, Long balance, List<BankAccount> bankAccounts, List<Transaction> transactions) {
        this.id = id;
        this.user = user;
        this.balance = balance;
        this.bankAccounts = bankAccounts;
        this.transactions = transactions;
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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
