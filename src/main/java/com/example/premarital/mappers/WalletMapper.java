package com.example.premarital.mappers;

import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.models.Wallet;

public interface WalletMapper {
    WalletDTO toDTO(Wallet wallet);
    Wallet toEntity(WalletDTO dto);
    Wallet toEntityWithId(Long id, WalletDTO dto);
    Wallet toEntityWithIdBalance(Long id, Long balance);
}
