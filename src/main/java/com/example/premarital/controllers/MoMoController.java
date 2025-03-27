package com.example.premarital.controllers;

import com.example.premarital.constant.MomoParameter;
import com.example.premarital.models.MomoResponse;
import com.example.premarital.services.impl.MoMoService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/ipn-handler")
    public String ipnHandler(@RequestParam Map<String, String> request) {
        Integer resultCode = Integer.valueOf(request.get(MomoParameter.RESULT_CODE));
        return resultCode == 0 ? "Giao dịch thành công" : "Giao dịch thất bại";
    }

}
