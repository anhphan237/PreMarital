package com.example.premarital.dtos;

public class BankAccountDTO {
    private Long id;
    private Long walletId;
    private String bankName;
    private String bankNumber;

    public BankAccountDTO() {
    }

    public BankAccountDTO(Long id, Long walletId, String bankName, String bankNumber) {
        this.id = id;
        this.walletId = walletId;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
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
