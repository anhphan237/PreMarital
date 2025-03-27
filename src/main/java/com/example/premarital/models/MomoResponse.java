package com.example.premarital.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MomoResponse {
    private String partnerCode;
    private String orderId;
    private String requestId;
    private Long amount;
    private long responseTime;
    private String message;
    private int resultCode;
    private String payUrl;
    private String deepLink;
    private String qrCodeUrl;
}

