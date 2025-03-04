package com.example.premarital.services.impl;

import com.example.premarital.dtos.CategoryDTO;
import com.example.premarital.mappers.BankAccountMapper;
import com.example.premarital.models.Category;
import com.example.premarital.services.BankAccountService;
import com.example.premarital.dtos.BankAccountDTO;
import com.example.premarital.models.BankAccount;
import com.example.premarital.repositories.BankAccountRepository;
import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, BankAccountMapper bankAccountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    public Page<BankAccountDTO> getBankAccounts(Pageable pageable) {
        Page<BankAccount> entities = bankAccountRepository.findAll(pageable);
        Page<BankAccountDTO> dtoPage = entities.map(new Function<BankAccount, BankAccountDTO>() {

            @Override
            public BankAccountDTO apply(BankAccount bankAccount) {
                BankAccountDTO dto = bankAccountMapper.toDTO(bankAccount);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createBankAccount(BankAccountDTO dto) {
        BankAccount bankAccount = bankAccountMapper.toEntity(dto);
        bankAccountRepository.save(bankAccount);
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
