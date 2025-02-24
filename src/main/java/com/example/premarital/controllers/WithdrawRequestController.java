package com.example.premarital.controllers;

import com.example.premarital.services.WithdrawRequestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/withdrawRequests")
public class WithdrawRequestController {
    private final WithdrawRequestService withdrawRequestService;

    public WithdrawRequestController(WithdrawRequestService withdrawRequestService) {
        this.withdrawRequestService = withdrawRequestService;
    }
}
