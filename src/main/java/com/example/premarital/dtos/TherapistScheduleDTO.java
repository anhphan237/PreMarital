package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TherapistScheduleDTO {
    private Long id;

    @NotNull(message = "Therapist ID is required")
    @Positive(message = "Therapist ID must be a positive number")
    private Long therapistId;

    @NotNull(message = "Available date is required")
    @FutureOrPresent(message = "Available date must be today or in the future")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate availableDate;

    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Start time must be in the future")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    @FutureOrPresent(message = "End time must be in the future")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime endTime;

    @JsonProperty("isBooked")
    private boolean isBooked;

    @JsonProperty("isActive")
    private boolean isActive;
}
