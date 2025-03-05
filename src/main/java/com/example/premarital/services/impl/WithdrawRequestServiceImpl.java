package com.example.premarital.services.impl;

import com.example.premarital.dtos.WithdrawRequestDTO;
import com.example.premarital.mappers.WithdrawRequestMapper;
import com.example.premarital.models.WithdrawRequest;
import com.example.premarital.repositories.WithdrawRequestRepository;
import com.example.premarital.services.WithdrawRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class WithdrawRequestServiceImpl implements WithdrawRequestService {
    private final WithdrawRequestRepository withdrawRequestRepository;
    private final WithdrawRequestMapper withdrawRequestMapper;

    public WithdrawRequestServiceImpl(WithdrawRequestRepository withdrawRequestRepository, WithdrawRequestMapper withdrawRequestMapper) {
        this.withdrawRequestRepository = withdrawRequestRepository;
        this.withdrawRequestMapper = withdrawRequestMapper;
    }

    @Override
    public Page<WithdrawRequestDTO> getWithdrawRequests(Pageable pageable) {
        Page<WithdrawRequest> entities = withdrawRequestRepository.findAll(pageable);
        Page<WithdrawRequestDTO> dtoPage = entities.map(new Function<WithdrawRequest, WithdrawRequestDTO>() {

            @Override
            public WithdrawRequestDTO apply(WithdrawRequest withdrawRequest) {
                WithdrawRequestDTO dto = withdrawRequestMapper.toDTO(withdrawRequest);
                return dto;
            }
        });
        return dtoPage;
    }

    @Override
    public void createWithdrawRequest(WithdrawRequestDTO dto) {
        WithdrawRequest withdrawRequest = withdrawRequestMapper.toEntity(dto);
        withdrawRequestRepository.save(withdrawRequest);
    }

    @Override
    public WithdrawRequest getWithdrawRequestById(Long id) {
        return null;
    }

    @Override
    public boolean deleteWithdrawRequestById(Long id) {
        return false;
    }

    @Override
    public boolean updateWithdrawRequest(Long id, WithdrawRequestDTO updatedWithdrawRequestDTO) {
        return false;
    }
}
