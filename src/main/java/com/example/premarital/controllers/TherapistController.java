package com.example.premarital.controllers;

import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.models.Therapist;
import com.example.premarital.services.TherapistService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/therapists")
public class TherapistController {
    private final TherapistService therapistService;

    public TherapistController(TherapistService therapistService) {
        this.therapistService = therapistService;
    }

    @GetMapping
    public ResponseEntity<?> getTherapists(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        if (page < 1 || size <= 1) {
            return ResponseEntity.badRequest().body("Page number must be >= 1 and size must be > 1");
        }

        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                direction != null ? direction : Sort.Direction.ASC,
                sort != null ? sort : "id"
        );
        Page<TherapistDTO> therapists = therapistService.getTherapists(pageable);
        return new ResponseEntity<>(therapists, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTherapist(@Valid @RequestBody TherapistDTO therapist){
        TherapistDTO newTherapist = therapistService.createTherapist(therapist);
        if(newTherapist == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Therapist already exists");
        return new ResponseEntity<>("Therapist created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TherapistDTO> findTherapistById(@PathVariable Long id){
        TherapistDTO therapist = therapistService.getTherapistById(id);
        return new ResponseEntity<>(therapist, therapist != null ? org.springframework.http.HttpStatus.OK : org.springframework.http.HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTherapist(@PathVariable Long id) {
        boolean deleted = therapistService.deleteTherapistById(id);
        return deleted
                ? ResponseEntity.ok("Therapist deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Therapist not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTherapist(@PathVariable Long id, @Valid @RequestBody TherapistDTO updatedTherapistDTO) {
        boolean updated = therapistService.updateTherapist(id, updatedTherapistDTO);
        return updated
                ? ResponseEntity.ok("Therapist updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Therapist not found");
    }
}
