package com.example.premarital.client;

import com.example.premarital.models.MomoRequest;
import com.example.premarital.models.MomoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "momo", url = "https://test-payment.momo.vn/v2/gateway/api")
public interface MomoApi {

    @PostMapping(value = "/create")
    MomoResponse createMomoQR(@RequestBody MomoRequest createMomoRequest);
}
