package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.mappers.WalletMapper;
import com.example.premarital.models.User;
import com.example.premarital.models.Wallet;
import com.example.premarital.repositories.UserRepository;
import com.example.premarital.repositories.WalletRepository;
import org.springframework.stereotype.Component;

@Component
public class WalletMapperImpl implements WalletMapper {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public WalletMapperImpl(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public WalletDTO toDTO(Wallet wallet) {
        if (wallet == null) {
            return null;
        }
        return new WalletDTO(
                wallet.getId(),
                wallet.getUser() != null ? wallet.getUser().getId() : null,
                wallet.getBalance(),
                wallet.getIsActive()
        );
    }

    @Override
    public Wallet toEntity(WalletDTO dto) {
        if (dto == null) {
            return null;
        }

        Wallet wallet = new Wallet();
        wallet.setId(dto.getId());
        wallet.setBalance(dto.getBalance());
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            wallet.setUser(user);
        }
        wallet.setIsActive(dto.getIsActive());

        return wallet;
    }

    @Override
    public Wallet toEntityWithId(Long id, WalletDTO dto) {
        if (dto == null) {
            return null;
        }

        Wallet wallet = new Wallet();
        wallet.setId(id);
        wallet.setBalance(dto.getBalance());
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            wallet.setUser(user);
        }
        wallet.setIsActive(dto.getIsActive());

        return wallet;
    }

    @Override
    public Wallet toEntityWithIdBalance(Long id, Long balance) {
        Wallet wallet = walletRepository.getReferenceById(id);
        wallet.setBalance(balance);

        return wallet;
    }
}
