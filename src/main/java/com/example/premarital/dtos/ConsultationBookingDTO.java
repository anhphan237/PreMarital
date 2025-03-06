package com.example.premarital.dtos;

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
}
