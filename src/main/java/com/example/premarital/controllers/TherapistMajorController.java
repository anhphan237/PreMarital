package com.example.premarital.controllers;

import com.example.premarital.common.pagination.PaginationRequest;
import com.example.premarital.common.pagination.PagingResult;
import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.dtos.TherapistMajorDTO;
import com.example.premarital.dtos.UserDTO;
import com.example.premarital.models.Role;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.services.TherapistMajorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/therapistMajors")
public class TherapistMajorController {
    private final TherapistMajorService therapistMajorService;

    public TherapistMajorController(TherapistMajorService therapistMajorService) {
        this.therapistMajorService = therapistMajorService;
    }

    @GetMapping
    public ResponseEntity<Page<TherapistMajorDTO>> findAll(
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
        Page<TherapistMajorDTO> therapistMajors = therapistMajorService.getTherapistMajors(pageable);
        return new ResponseEntity<>(therapistMajors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTherapistMajor(@RequestBody TherapistMajorDTO therapistMajorDTO){
        therapistMajorService.createTherapistMajor(therapistMajorDTO);
        return new ResponseEntity<>("Therapist major created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TherapistMajor> findTherapistMajorById(@PathVariable Long id){
        TherapistMajor therapistMajor = therapistMajorService.getTherapistMajorById(id);
        return new ResponseEntity<>(therapistMajor, therapistMajor != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTherapistMajor(@PathVariable Long id) {
        boolean deleted = therapistMajorService.deleteTherapistMajorById(id);
        return deleted
                ? ResponseEntity.ok("Therapist Major deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Therapist Major not found");
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateRole(@PathVariable Long id, @RequestBody TherapistMajor updatedTherapistMajor) {
//        boolean updated = therapistMajorService.updateTherapistMajor(id, updatedTherapistMajor);
//        return updated
//                ? ResponseEntity.ok("Therapist Major updated successfully")
//                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Therapist Major not found");
//    }
}
