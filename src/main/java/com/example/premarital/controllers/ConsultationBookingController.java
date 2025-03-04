package com.example.premarital.controllers;

import com.example.premarital.services.ConsultationBookingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultationBookings")
public class ConsultationBookingController {
    private final ConsultationBookingService consultationBookingService;

    public ConsultationBookingController(ConsultationBookingService consultationBookingService) {
        this.consultationBookingService = consultationBookingService;
    }
}
