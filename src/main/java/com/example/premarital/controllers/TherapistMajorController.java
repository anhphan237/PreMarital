package com.example.premarital.controllers;

import com.example.premarital.services.TherapistMajorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/therapistMajors")
public class TherapistMajorController {
    private final TherapistMajorService therapistMajorService;

    public TherapistMajorController(TherapistMajorService therapistMajorService) {
        this.therapistMajorService = therapistMajorService;
    }
}
