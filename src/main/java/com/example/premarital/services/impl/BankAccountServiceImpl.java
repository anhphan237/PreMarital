package com.example.premarital.services.impl;

import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.mappers.BankAccountMapper;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.services.BankAccountService;
import com.example.premarital.dtos.BankAccountDTO;
import com.example.premarital.models.BankAccount;
import com.example.premarital.repositories.BankAccountRepository;
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
    public BankAccountDTO getBankAccountById(Long id) {
        return bankAccountMapper.toDTO(bankAccountRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteBankAccountById(Long id) {
        return bankAccountRepository.findById(id).map(bankAccount -> {
            bankAccount.setIsActive(false);
            bankAccountRepository.save(bankAccount);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateBankAccount(Long id, BankAccountDTO updatedBankAccountDTO) {
        return bankAccountRepository.findById(id).map(therapistMajor -> {
            BankAccount updatedBankAccount = bankAccountMapper.toEntityWithId(id, updatedBankAccountDTO);
            bankAccountRepository.save(updatedBankAccount);
            return true;
        }).orElse(false);
    }
}
