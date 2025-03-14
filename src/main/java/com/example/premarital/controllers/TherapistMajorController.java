package com.example.premarital.controllers;

import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.services.TherapistMajorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/therapistMajors")
public class TherapistMajorController {
    private final TherapistMajorService therapistMajorService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ){
        if (page < 1 || size <= 1) {
            return ResponseEntity.badRequest().body("Page number must be >= 1 and size must be > 1");
        }

        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                direction != null ? direction : Sort.Direction.ASC,
                sort != null ? sort : "id"
        );
        Page<TherapistMajorDTO> therapistMajors = therapistMajorService.getTherapistMajors(pageable);
        return new ResponseEntity<>(therapistMajors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTherapistMajor(@Valid @RequestBody TherapistMajorDTO therapistMajorDTO){
        therapistMajorService.createTherapistMajor(therapistMajorDTO);
        return new ResponseEntity<>("Therapist major created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TherapistMajorDTO> findTherapistMajorById(@PathVariable Long id){
        TherapistMajorDTO therapistMajor = therapistMajorService.getTherapistMajorById(id);
        return new ResponseEntity<>(therapistMajor, therapistMajor != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTherapistMajor(@PathVariable Long id) {
        boolean deleted = therapistMajorService.deleteTherapistMajorById(id);
        return deleted
                ? ResponseEntity.ok("Therapist Major deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Therapist Major not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTherapistMajor(@PathVariable Long id, @Valid @RequestBody TherapistMajorDTO updatedTherapistMajor) {
        boolean updated = therapistMajorService.updateTherapistMajor(id, updatedTherapistMajor);
        return updated
                ? ResponseEntity.ok("Therapist Major updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Therapist Major not found");
    }
}
