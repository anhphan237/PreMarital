package com.example.premarital.mappers;

import com.example.premarital.dtos.WithdrawRequestDTO;
import com.example.premarital.models.WithdrawRequest;

public interface WithdrawRequestMapper {
    WithdrawRequestDTO toDTO(WithdrawRequest withdrawRequest);

    WithdrawRequest toEntity(WithdrawRequestDTO dto);
}
