package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;

    @NotNull(message = "Wallet ID cannot be null")
    private Long walletId;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount must be at least 1")
    private Long amount;

    @NotBlank(message = "Transaction type is required")
    @Pattern(regexp = "DEPOSIT|WITHDRAWAL|TRANSFER", message = "Transaction type must be DEPOSIT, WITHDRAWAL, or TRANSFER")
    private String transactionType;

    @NotNull(message = "Transaction time cannot be null")
    @PastOrPresent(message = "Transaction time must be in the past or present")
    private Date transactionTime;

    @NotBlank(message = "Transaction status is required")
    @Pattern(regexp = "PENDING|COMPLETED|FAILED", message = "Transaction status must be PENDING, COMPLETED, or FAILED")
    private String transactionStatus;

    @NotNull(message = "Balance before transaction cannot be null")
    @Min(value = 0, message = "Balance before transaction must be at least 0")
    private Long balanceBefore;

    @NotNull(message = "Transaction fee cannot be null")
    @Min(value = 0, message = "Transaction fee must be at least 0")
    private Long transactionFee;

    @NotNull(message = "Total amount cannot be null")
    @Min(value = 1, message = "Total amount must be at least 1")
    private Long totalAmount;

    private Long withdrawRequestId;

    @JsonProperty("isActive")
    @NotNull(message = "Active status is required")
    private Boolean isActive;
}
