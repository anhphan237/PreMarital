package com.example.premarital.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MomoRequest {
    private String partnerCode;
    private String requestType;
    private String ipnUrl;
    private String orderId;
    private Long amount;
    private String orderInfo;
    private String requestId;
    private String redirectUrl;
    private String extraData;
    private String signature;
    private String lang;
}

