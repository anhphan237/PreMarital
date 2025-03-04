package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.WalletDTO;
import com.example.premarital.models.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WalletService {
    Page<WalletDTO> getWallets(Pageable pageable);
    void createWallet(WalletDTO dto);
    Wallet getWalletById(Long id);
    boolean deleteWalletById(Long id);
    boolean updateWallet(Long id, WalletDTO updatedWalletDTO);
}
