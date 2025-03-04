package com.example.premarital.controllers;

import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.dtos.TherapistScheduleDTO;
import com.example.premarital.services.TherapistScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/therapistSchedules")
public class TherapistScheduleController {
    private final TherapistScheduleService therapistScheduleService;

    public TherapistScheduleController(TherapistScheduleService therapistScheduleService) {
        this.therapistScheduleService = therapistScheduleService;
    }

    @GetMapping
    public ResponseEntity<Page<TherapistScheduleDTO>> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Sort.Direction direction
    ){
        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                direction != null ? direction : Sort.Direction.ASC,
                sort != null ? sort : "id"
        );
        Page<TherapistScheduleDTO> therapistSchedules = therapistScheduleService.getTherapistSchedules(pageable);
        return new ResponseEntity<>(therapistSchedules, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTherapistSchedule(@RequestBody TherapistScheduleDTO therapistScheduleDTO){
        therapistScheduleService.createTherapistSchedule(therapistScheduleDTO);
        return new ResponseEntity<>("Therapist schedule created successfully",HttpStatus.CREATED);
    }
}
