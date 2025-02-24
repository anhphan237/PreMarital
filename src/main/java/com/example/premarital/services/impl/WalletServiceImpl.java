package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.models.Wallet;
import com.example.premarital.repositories.WalletRepository;
import com.example.premarital.services.WalletService;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public PagingResult<WalletDTO> getWallets(PaginationRequest request) {
        return null;
    }

    @Override
    public WalletDTO createWallet(WalletDTO dto) {
        return null;
    }

    @Override
    public Wallet getWalletById(Long id) {
        return null;
    }

    @Override
    public boolean deleteWalletById(Long id) {
        return false;
    }

    @Override
    public boolean updateWallet(Long id, WalletDTO updatedWalletDTO) {
        return false;
    }
}
