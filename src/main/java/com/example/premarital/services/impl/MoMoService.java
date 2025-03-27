package com.example.premarital.services.impl;

import com.example.premarital.client.MomoApi;
import com.example.premarital.models.MomoRequest;
import com.example.premarital.models.MomoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoMoService {

    @Value(value = "MOMO")
    private String PARTNER_CODE;

    @Value(value = "F8BBA842ECF85")
    private String ACCESS_KEY;

    @Value(value = "K951B6PE1waDMi640xX08PD3vg6EkVlz")
    private String SECRET_KEY;

    @Value(value = "http://localhost:5173/")
    private String REDIRECT_URL;

    @Value(value = "http://localhost:8080/api/momo/ipn-handler")
    private String IPN_URL;

    @Value(value = "payWithCC")
    private String REQUEST_TYPE;

    private final MomoApi momoApi;

    public MomoResponse createMomoQR() {
        String orderId = UUID.randomUUID().toString();
        String orderInfo = "Thanh toán đơn hàng: " + orderId;
        String requestId = UUID.randomUUID().toString();
        String extraData = "";
        Long amount = 100000L;

        String rawSignature = String.format(
                "accessKey=%s&amount=%d&extraData=%s&ipnUrl=%s&orderId=%s&orderInfo=%s&partnerCode=%s&redirectUrl=%s&requestId=%s&requestType=%s",
                ACCESS_KEY, amount, extraData, IPN_URL, orderId, orderInfo, PARTNER_CODE, REDIRECT_URL, requestId, REQUEST_TYPE
        );

        log.info(rawSignature);

        String prettySignature = "";
        try {
            prettySignature = signHmacSHA256(rawSignature, SECRET_KEY);
        } catch (Exception e) {
            log.error(">>> Có lỗi khi hash code: " + e);
            return null;
        }

        if (prettySignature.isBlank()) {
            log.error(">>> signature is blank");
            return null;
        }

        MomoRequest request = MomoRequest.builder()
                .partnerCode(PARTNER_CODE)
                .requestType(REQUEST_TYPE)
                .ipnUrl(IPN_URL)
                .redirectUrl(REDIRECT_URL)
                .orderId(orderId)
                .orderInfo(orderInfo)
                .requestId(requestId)
                .extraData(extraData)
                .amount(amount)
                .signature(prettySignature)
                .lang("vi")
                .build();

        // Gửi request đến API MoMo (cần FeignClient hoặc RestTemplate)
        log.info(">>> request: " + request);
        return momoApi.createMomoQR(request);
    }

    private String signHmacSHA256(String data, String key) throws Exception {
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        hmacSHA256.init(secretKey);

        byte[] hash = hmacSHA256.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

}
