package com.example.premarital.controllers;

import com.example.premarital.services.TherapistScheduleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/therapistSchedules")
public class TherapistScheduleController {
    private final TherapistScheduleService therapistScheduleService;

    public TherapistScheduleController(TherapistScheduleService therapistScheduleService) {
        this.therapistScheduleService = therapistScheduleService;
    }
}
