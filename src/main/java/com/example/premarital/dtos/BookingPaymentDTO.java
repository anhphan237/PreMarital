package com.example.premarital.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingPaymentDTO {

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    @NotNull(message = "Therapist ID cannot be null")
    private Long therapistId;

    @NotNull(message = "Booking ID cannot be null")
    private Long bookingId;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount must be greater than 0")
    private Long amount;
}
