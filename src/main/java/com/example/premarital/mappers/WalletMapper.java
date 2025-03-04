package com.example.premarital.mappers;

import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.models.Wallet;

public interface WalletMapper {
    WalletDTO toDTO(Wallet wallet);

    Wallet toEntity(WalletDTO dto);
}
