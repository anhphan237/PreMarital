package com.example.premarital.services;

import com.example.premarital.dtos.WalletDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WalletService {
    Page<WalletDTO> getWallets(Pageable pageable);
    void createWallet(WalletDTO dto);
    WalletDTO getWalletById(Long id);
    WalletDTO getWalletByUserId(Long userId);
    boolean deleteWalletById(Long id);
    boolean updateWallet(Long id, WalletDTO updatedWalletDTO);
    boolean updateWalletBalance(Long id, Long balance);
}
