package com.example.premarital.services.impl;

import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.mappers.WalletMapper;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.models.Wallet;
import com.example.premarital.repositories.WalletRepository;
import com.example.premarital.services.WalletService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public WalletServiceImpl(WalletRepository walletRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public Page<WalletDTO> getWallets(Pageable pageable) {
        Page<Wallet> entities = walletRepository.findAll(pageable);
        Page<WalletDTO> dtoPage = entities.map(new Function<Wallet, WalletDTO>() {

            @Override
            public WalletDTO apply(Wallet wallet) {
                WalletDTO dto = walletMapper.toDTO(wallet);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createWallet(WalletDTO dto) {
        walletRepository.save(walletMapper.toEntity(dto));
    }

    @Override
    public WalletDTO getWalletById(Long id) {
        return walletMapper.toDTO(walletRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteWalletById(Long id) {
        return walletRepository.findById(id).map(wallet -> {
            wallet.setIsActive(false);
            walletRepository.save(wallet);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateWallet(Long id, WalletDTO updatedWalletDTO) {
        return walletRepository.findById(id).map(wallet -> {
            Wallet updatedWallet = walletMapper.toEntityWithId(id, updatedWalletDTO);
            walletRepository.save(updatedWallet);
            return true;
        }).orElse(false);
    }
}
