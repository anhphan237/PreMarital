package com.example.premarital.controllers;

import com.example.premarital.dtos.ConsultationBookingDTO;
import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.models.ConsultationBooking;
import com.example.premarital.models.Role;
import com.example.premarital.services.ConsultationBookingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultationBookings")
public class ConsultationBookingController {
    private final ConsultationBookingService consultationBookingService;

    public ConsultationBookingController(ConsultationBookingService consultationBookingService) {
        this.consultationBookingService = consultationBookingService;
    }

    @GetMapping
    public ResponseEntity<Page<ConsultationBookingDTO>> findAll(
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
        Page<ConsultationBookingDTO> consultationBookingDTOS = consultationBookingService.getConsultationBookings(pageable);
        return new ResponseEntity<>(consultationBookingDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createConsultationBooking(@RequestBody ConsultationBookingDTO consultationBookingDTO){
        consultationBookingService.createConsultationBooking(consultationBookingDTO);
        return new ResponseEntity<>("Consultation Booking created successfully",HttpStatus.CREATED);
    }
}
