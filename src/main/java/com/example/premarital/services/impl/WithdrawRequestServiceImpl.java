package com.example.premarital.services.impl;

import com.example.premarital.dtos.WithdrawRequestDTO;
import com.example.premarital.mappers.WithdrawRequestMapper;
import com.example.premarital.models.WithdrawRequest;
import com.example.premarital.repositories.WithdrawRequestRepository;
import com.example.premarital.services.WithdrawRequestService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class WithdrawRequestServiceImpl implements WithdrawRequestService {
    private final WithdrawRequestRepository withdrawRequestRepository;
    private final WithdrawRequestMapper withdrawRequestMapper;

    @Override
    public Page<WithdrawRequestDTO> getWithdrawRequests(Pageable pageable) {
        Page<WithdrawRequest> entities = withdrawRequestRepository.findAll(pageable);
        return entities.map(new Function<WithdrawRequest, WithdrawRequestDTO>() {

            @Override
            public WithdrawRequestDTO apply(WithdrawRequest withdrawRequest) {
                return withdrawRequestMapper.toDTO(withdrawRequest);
            }
        });
    }

    @Override
    public void createWithdrawRequest(WithdrawRequestDTO dto) {
        WithdrawRequest withdrawRequest = withdrawRequestMapper.toEntity(dto);
        withdrawRequestRepository.save(withdrawRequest);
    }

    @Override
    public WithdrawRequestDTO getWithdrawRequestById(Long id) {
        return withdrawRequestMapper.toDTO(withdrawRequestRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteWithdrawRequestById(Long id) {
        return withdrawRequestRepository.findById(id).map(withdrawRequest -> {
            withdrawRequest.setIsActive(false);
            withdrawRequestRepository.save(withdrawRequest);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateWithdrawRequest(Long id, WithdrawRequestDTO updatedWithdrawRequestDTO) {
        return withdrawRequestRepository.findById(id).map(withdrawRequest -> {
            WithdrawRequest updatedWithdrawRequest = withdrawRequestMapper.toEntityWithId(id, updatedWithdrawRequestDTO);
            withdrawRequestRepository.save(updatedWithdrawRequest);
            return true;
        }).orElse(false);
    }
}
