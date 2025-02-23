package com.example.premarital.bankAccount.service;

import com.example.premarital.bankAccount.dto.BankAccountDTO;
import com.example.premarital.bankAccount.model.BankAccount;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;

public interface BankAccountService{
    PagingResult<BankAccountDTO> getBankAccounts(PaginationRequest request);
    BankAccountDTO createBankAccount(BankAccountDTO dto);
    BankAccount getBankAccountById(Long id);
    boolean deleteBankAccountById(Long id);
    boolean updateBankAccount(Long id, BankAccountDTO updatedBankAccountDTO);
}
