package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TherapistDTO {
    @ToString.Exclude
    private Long userId;

    @Size(max = 500, message = "Bio cannot exceed 500 characters")
    private String bio;

    @NotBlank(message = "Certification name is required")
    private String therapistCertificationName;

    @NotBlank(message = "Certification issuer is required")
    private String certificationIssuedBy;

    @NotNull(message = "Certification issue date is required")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @PastOrPresent(message = "Issue date cannot be in the future")
    private LocalDate certificationIssueDate;

    @NotNull(message = "Certification expiration date is required")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @FutureOrPresent(message = "Expiration date must be today or in the future")
    private LocalDate certificationExpirationDate;

    @ToString.Exclude
    @NotNull(message = "Therapist Major ID cannot be null")
    private Long therapistMajorId;

    private Boolean isActive;

    private Long version;
}
