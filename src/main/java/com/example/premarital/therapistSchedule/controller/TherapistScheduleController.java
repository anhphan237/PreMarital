package com.example.premarital.therapistSchedule.controller;

import com.example.premarital.therapistSchedule.model.TherapistSchedule;
import com.example.premarital.therapistSchedule.service.TherapistScheduleService;
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
