package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private Long walletId;
    private Long amount;
    private String transactionType;
    private Date transactionTime;
    private String transactionStatus;
    private Long balanceBefore;
    private Long transactionFee;
    private Long totalAmount;
    private Long withdrawRequestId;
    @JsonProperty("isActive")
    private Boolean isActive;
}
