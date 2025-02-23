package com.example.premarital.wallet.service;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.user.dto.UserDTO;
import com.example.premarital.wallet.dto.WalletDTO;
import com.example.premarital.wallet.model.Wallet;

public interface WalletService {
    PagingResult<WalletDTO> getWallets(PaginationRequest request);
    WalletDTO createWallet(WalletDTO dto);
    Wallet getWalletById(Long id);
    boolean deleteWalletById(Long id);
    boolean updateWallet(Long id, WalletDTO updatedWalletDTO);
}
