package com.example.premarital.therapistMajor.controller;

import com.example.premarital.therapistMajor.service.TherapistMajorService;
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
