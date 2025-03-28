package com.example.premarital.controllers;

import com.example.premarital.constant.MomoParameter;
import com.example.premarital.models.MomoResponse;
import com.example.premarital.services.impl.MoMoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/momo")
public class MoMoController {
    private final MoMoService momoService;

    public MoMoController(MoMoService momoService) {
        this.momoService = momoService;
    }

    @PostMapping("/create")
    public MomoResponse createQR(){
        return momoService.createMomoQR();
    }

    @PostMapping("/ipn-handler")
    public String ipnHandler(@RequestBody Map<String, String> request) {
        log.info("Received MoMo IPN: {}", request);

        Integer resultCode = Integer.valueOf(request.get(MomoParameter.RESULT_CODE));

        if (resultCode == 0) {
            log.info("Giao dịch thành công");
        } else {
            log.info("Giao dịch thất bại");
        }

        return resultCode == 0 ? "Giao dịch thành công" : "Giao dịch thất bại";
    }

}
