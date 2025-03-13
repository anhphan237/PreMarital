package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationBookingDTO {
    private Long id;

    @NotNull(message = "TherapistScheduleId is required")
    private Long therapistScheduleId;

    @NotNull(message = "UserId is required")
    private Long userId;

    @NotNull(message = "TransactionId is required")
    private Long transactionId;

    @NotBlank(message = "Status is required")
    @Size(max = 50, message = "Status cannot exceed 50 characters")
    private String status;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be a positive number")
    private Long amount;

    @NotBlank(message = "Meet URL is required")
    @Size(max = 255, message = "Meet URL cannot exceed 255 characters")
    private String meetUrl;

    @NotNull(message = "CategoryId is required")
    private Long categoryId;

    @JsonProperty("isActive")
    private Boolean isActive;
}
