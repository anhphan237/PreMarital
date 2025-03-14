package com.example.premarital.controllers;

import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.dtos.TherapistScheduleDTO;
import com.example.premarital.services.TherapistScheduleService;
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
@RequestMapping("/therapistSchedules")
public class TherapistScheduleController {
    private final TherapistScheduleService therapistScheduleService;

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

        Page<TherapistScheduleDTO> therapistSchedules = therapistScheduleService.getTherapistSchedules(pageable);
        if (therapistSchedules.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(therapistSchedules);
    }

    @PostMapping
    public ResponseEntity<String> createTherapistSchedule(@Valid @RequestBody TherapistScheduleDTO therapistScheduleDTO){
        therapistScheduleService.createTherapistSchedule(therapistScheduleDTO);
        return new ResponseEntity<>("Therapist schedule created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TherapistScheduleDTO> findTherapistScheduleById(@PathVariable Long id){
        TherapistScheduleDTO therapistSchedule = therapistScheduleService.getTherapistScheduleById(id);
        return new ResponseEntity<>(therapistSchedule, therapistSchedule != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTherapistSchedule(@PathVariable Long id) {
        boolean deleted = therapistScheduleService.deleteTherapistScheduleById(id);
        return deleted
                ? ResponseEntity.ok("Therapist Schedule deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Therapist Schedule not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTherapistSchedule(@PathVariable Long id, @Valid @RequestBody TherapistScheduleDTO updatedTherapistSchedule) {
        boolean updated = therapistScheduleService.updateTherapistSchedule(id, updatedTherapistSchedule);
        return updated
                ? ResponseEntity.ok("Therapist's Schedule updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Therapist's Schedule not found");
    }
}
