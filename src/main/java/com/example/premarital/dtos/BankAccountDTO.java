package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO {
    private Long id;

    @NotNull(message = "Wallet ID cannot be null")
    private Long walletId;

    @NotBlank(message = "Bank name cannot be empty")
    @Size(max = 100, message = "Bank name must not exceed 100 characters")
    private String bankName;

    @NotBlank(message = "Bank number cannot be empty")
    @Pattern(regexp = "\\d{8,20}", message = "Bank number must contain 8-20 digits")
    private String bankNumber;

    @JsonProperty("isActive")
    private Boolean isActive;
}
