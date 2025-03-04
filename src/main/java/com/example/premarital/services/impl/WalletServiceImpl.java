package com.example.premarital.services.impl;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.BankAccountDTO;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.mappers.WalletMapper;
import com.example.premarital.models.BankAccount;
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
        Wallet wallet = new Wallet();
        walletRepository.save(wallet);
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
