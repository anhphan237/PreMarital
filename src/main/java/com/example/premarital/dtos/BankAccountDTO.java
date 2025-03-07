package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO {
    private Long id;
    private Long walletId;
    private String bankName;
    private String bankNumber;
    @JsonProperty("isActive")
    private Boolean isActive;
}
