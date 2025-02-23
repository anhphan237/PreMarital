package com.example.premarital.therapist.controller;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.therapist.dto.TherapistDTO;
import com.example.premarital.therapist.model.Therapist;
import com.example.premarital.therapist.service.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<PagingResult<TherapistDTO>> getTherapists(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) Sort.Direction direction
    ) {
        final PaginationRequest request = new PaginationRequest(page - 1, size, sortField, direction);
        final PagingResult<TherapistDTO> therapists = therapistService.getTherapists(request);
        return new ResponseEntity<>(therapists, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTherapist(@RequestBody TherapistDTO therapist){
        TherapistDTO newTherapist = therapistService.createTherapist(therapist);
        if(newTherapist == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Therapist already exists");
        return new ResponseEntity<>("Therapist created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Therapist> findTherapistById(@PathVariable Long id){
        Therapist therapist = therapistService.getTherapistById(id);
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
    public ResponseEntity<String> updateTherapist(@PathVariable Long id, @RequestBody TherapistDTO updatedTherapistDTO) {
        boolean updated = therapistService.updateTherapist(id, updatedTherapistDTO);
        return updated
                ? ResponseEntity.ok("Therapist updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Therapist not found");
    }
}
