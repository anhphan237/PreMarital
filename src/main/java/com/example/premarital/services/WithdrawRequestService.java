package com.example.premarital.services;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.WithdrawRequestDTO;
import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WithdrawRequestService {
    Page<WithdrawRequestDTO> getWithdrawRequests(Pageable pageable);
    void createWithdrawRequest(WithdrawRequestDTO dto);
    WithdrawRequest getWithdrawRequestById(Long id);
    boolean deleteWithdrawRequestById(Long id);
    boolean updateWithdrawRequest(Long id, WithdrawRequestDTO updatedWithdrawRequestDTO);
}
