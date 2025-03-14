package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawRequestDTO {
    private Long id;

    @NotNull(message = "User ID must not be null")
    private Long userId;

    @NotNull(message = "Request amount must not be null")
    @Positive(message = "Request amount must be greater than 0")
    private Long requestAmount;

    @NotNull(message = "Request date must not be null")
    @PastOrPresent(message = "Request date cannot be in the future")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate requestDate;

    @JsonProperty("isApproved")
    private Boolean isApproved;

    private Long approvedBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @PastOrPresent(message = "Approved date cannot be in the future")
    private LocalDate approvedDate;

    private Long transactionId;

    @NotNull(message = "Active status must not be null")
    @JsonProperty("isActive")
    private Boolean isActive;
}
