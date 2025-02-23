package com.example.premarital.bankAccount.service;

import com.example.premarital.bankAccount.dto.BankAccountDTO;
import com.example.premarital.bankAccount.model.BankAccount;
import com.example.premarital.bankAccount.repository.BankAccountRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService{
    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public PagingResult<BankAccountDTO> getBankAccounts(PaginationRequest request) {
        return null;
    }

    @Override
    public BankAccountDTO createBankAccount(BankAccountDTO dto) {
        return null;
    }

    @Override
    public BankAccount getBankAccountById(Long id) {
        return null;
    }

    @Override
    public boolean deleteBankAccountById(Long id) {
        return false;
    }

    @Override
    public boolean updateBankAccount(Long id, BankAccountDTO updatedBankAccountDTO) {
        return false;
    }
}
