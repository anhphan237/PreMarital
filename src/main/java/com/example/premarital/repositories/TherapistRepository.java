package com.example.premarital.repositories;

import com.example.premarital.models.Therapist;
import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapistRepository extends JpaRepository<Therapist, Long> {
    Page<Therapist> findTherapistsByIsActiveTrue(Pageable pageable);
    Therapist getByUser_Email(String email);
}
