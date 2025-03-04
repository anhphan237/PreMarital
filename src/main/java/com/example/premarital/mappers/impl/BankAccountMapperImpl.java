package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.BankAccountDTO;
import com.example.premarital.mappers.BankAccountMapper;
import com.example.premarital.models.BankAccount;
import com.example.premarital.repositories.WalletRepository;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapperImpl implements BankAccountMapper {
    private final WalletRepository walletRepository;

    public BankAccountMapperImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public BankAccountDTO toDTO(BankAccount bankAccount) {
        if (bankAccount == null) {
            return null;
        }
        return new BankAccountDTO(
                bankAccount.getId(),
                bankAccount.getWallet() != null ? bankAccount.getWallet().getId() : null,
                bankAccount.getBankName(),
                bankAccount.getBankNumber()
        );
    }

    @Override
    public BankAccount toEntity(BankAccountDTO dto) {
        if (dto == null) {
            return null;
        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(dto.getId());
        bankAccount.setBankName(dto.getBankName());
        bankAccount.setBankNumber(dto.getBankNumber());

        if (dto.getWalletId() != null) {
            bankAccount.setWallet(walletRepository.findById(dto.getWalletId()).orElse(null));
        }

        return bankAccount;
    }
}
