package com.example.premarital.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationBookingDTO {
    private Long id;
    private Long therapistScheduleId;
    private Long userId;
    private Long transactionId;
    private String status;
    private Long amount;
    private String meetUrl;
    private Long categoryId;
    @JsonProperty("isActive")
    private Boolean isActive;
}
