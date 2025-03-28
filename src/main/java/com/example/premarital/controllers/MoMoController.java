package com.example.premarital.controllers;

import com.example.premarital.constant.MomoParameter;
import com.example.premarital.dtos.MomoRequestDTO;
import com.example.premarital.models.MomoResponse;
import com.example.premarital.services.ConsultationBookingService;
import com.example.premarital.services.TransactionService;
import com.example.premarital.services.WalletService;
import com.example.premarital.services.impl.MoMoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/momo")
public class MoMoController {
    private final MoMoService momoService;

    @PostMapping("/create")
    public MomoResponse createQR(@RequestBody MomoRequestDTO dto){
        return momoService.createMomoQR(dto.getAmount());
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

    @PostMapping("/process-payment")
    public ResponseEntity<String> processPayment(@RequestParam Long amount,
                                                 @RequestParam String orderId,
                                                 @RequestParam int resultCode,
                                                 @RequestParam Long userId,
                                                 @RequestParam Long therapistId) {
        // Kiểm tra nếu thanh toán thành công (resultCode = 0)
        if (resultCode == 0) {
            boolean result = momoService.updateAll(orderId,amount,resultCode,userId,therapistId);

            return ResponseEntity.ok("Payment successful and data updated");
        } else {
            return ResponseEntity.badRequest().body("Payment failed");
        }
    }
}
