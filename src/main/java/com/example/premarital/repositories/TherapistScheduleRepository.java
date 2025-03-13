package com.example.premarital.repositories;

import com.example.premarital.models.TherapistSchedule;
import com.example.premarital.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapistScheduleRepository extends JpaRepository<TherapistSchedule, Long> {
    Page<TherapistSchedule> findSchedulesByIsActiveTrue(Pageable pageable);
}
