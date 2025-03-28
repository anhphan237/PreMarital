package com.example.premarital.controllers;

import com.example.premarital.dtos.BookingPaymentDTO;
import com.example.premarital.dtos.ConsultationBookingDTO;
import com.example.premarital.dtos.RoleDTO;
import com.example.premarital.dtos.TherapistScheduleDTO;
import com.example.premarital.models.ConsultationBooking;
import com.example.premarital.models.Role;
import com.example.premarital.services.ConsultationBookingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultationBookings")
public class ConsultationBookingController {
    private final ConsultationBookingService consultationBookingService;

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
        Page<ConsultationBookingDTO> consultationBookings = consultationBookingService.getConsultationBookings(pageable);
        if (consultationBookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(consultationBookings);
    }

    @PostMapping
    public ResponseEntity<String> createConsultationBooking(@Valid @RequestBody ConsultationBookingDTO consultationBookingDTO){
        consultationBookingService.createConsultationBooking(consultationBookingDTO);
        return new ResponseEntity<>("Consultation Booking created successfully",HttpStatus.CREATED);
    }

    @PostMapping("/payment")
    public ResponseEntity<String> processBookingPayment(@RequestBody BookingPaymentDTO bookingPaymentDTO) {
        try {
            boolean success = consultationBookingService.processBookingPayment(
                    bookingPaymentDTO.getCustomerId(),
                    bookingPaymentDTO.getTherapistId(),
                    bookingPaymentDTO.getBookingId(),
                    bookingPaymentDTO.getAmount()
            );

            if (success) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Payment processed successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Failed to process payment.");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Wallet not found: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Transaction failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationBookingDTO> findConsultationBookingById(@PathVariable Long id){
        ConsultationBookingDTO consultationBookingDTO = consultationBookingService.getConsultationBookingById(id);
        return new ResponseEntity<>(consultationBookingDTO, consultationBookingDTO != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConsultationBooking(@PathVariable Long id) {
        boolean deleted = consultationBookingService.deleteConsultationBookingById(id);
        return deleted
                ? ResponseEntity.ok("Booking deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateConsultationBooking(@PathVariable Long id, @Valid @RequestBody ConsultationBookingDTO consultationBookingDTO) {
        boolean updated = consultationBookingService.updateConsultationBooking(id, consultationBookingDTO);
        return updated
                ? ResponseEntity.ok("Booking updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
    }
}
