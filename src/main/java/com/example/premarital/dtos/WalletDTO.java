package com.example.premarital.dtos;

public class WalletDTO {
    private Long id;
    private Long userId;
    private Long balance;

    public WalletDTO() {
    }

    public WalletDTO(Long id, Long userId, Long balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
