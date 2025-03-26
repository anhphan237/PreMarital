package com.example.premarital.services.impl;

import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.mappers.BankAccountMapper;
import com.example.premarital.repositories.WalletRepository;
import com.example.premarital.services.BankAccountService;
import com.example.premarital.dtos.BankAccountDTO;
import com.example.premarital.models.BankAccount;
import com.example.premarital.repositories.BankAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;
    private static final Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);
    private final WalletRepository walletRepository;

    @Override
    public Page<BankAccountDTO> getBankAccounts(Pageable pageable) {
        Page<BankAccount> accounts = bankAccountRepository.findBankAccountsByIsActiveTrue(pageable);
        if (accounts.isEmpty()) {
            logger.warn("No active bank accounts found.");
        }
        return accounts.map(bankAccountMapper::toDTO);
    }

    @Override
    @Transactional
    public void createBankAccount(BankAccountDTO dto) {
        if (dto.getId() != null) {
            throw new InvalidDataException("ID must be null when creating a new bank account");
        }

        if (dto.getWalletId() == null || dto.getWalletId() <= 0 || !walletRepository.existsById(dto.getWalletId())) {
            throw new InvalidDataException("Wallet ID " + dto.getWalletId() + " is invalid or not found");
        }

        try {
            BankAccount bankAccount = bankAccountMapper.toEntity(dto);
            bankAccountRepository.save(bankAccount);
            logger.info("Bank account created successfully with ID: {}", bankAccount.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while creating bank account: {}", e.getMessage(), e);
            throw new InvalidDataException("Invalid bank account data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating bank account: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create bank account", e);
        }
    }

    @Override
    public BankAccountDTO getBankAccountById(Long id) {
        return bankAccountRepository.findById(id)
                .map(bankAccountMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Bank account with ID " + id + " not found"));
    }

    @Override
    @Transactional
    public boolean deleteBankAccountById(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bank account with ID " + id + " not found"));

        if (!bankAccount.getIsActive()) {
            logger.warn("Bank account with ID {} is already inactive", id);
            return false;
        }

        try {
            bankAccount.setIsActive(false);
            bankAccountRepository.save(bankAccount);
            logger.info("Bank account with ID {} has been deactivated", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deactivating bank account with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to deactivate bank account", e);
        }
    }

    @Override
    @Transactional
    public boolean updateBankAccount(Long id, BankAccountDTO updatedBankAccountDTO) {
        if (!bankAccountRepository.existsById(id)) {
            throw new EntityNotFoundException("Bank account ID " + id + " not found");
        }

        if (updatedBankAccountDTO.getWalletId() == null || updatedBankAccountDTO.getWalletId() <= 0 || !walletRepository.existsById(updatedBankAccountDTO.getWalletId())) {
            throw new InvalidDataException("Wallet ID " + updatedBankAccountDTO.getWalletId() + " is invalid or not found");
        }

        try {
            BankAccount updatedBankAccount = bankAccountMapper.toEntityWithId(id, updatedBankAccountDTO);
            bankAccountRepository.save(updatedBankAccount);
            logger.info("Bank account with ID {} updated successfully", id);
            return true;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while updating bank account with ID {}: {}", id, e.getMessage(), e);
            throw new InvalidDataException("Invalid update data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating bank account with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update bank account", e);
        }
    }
}
