package com.example.premarital.therapistSchedule.repository;

import com.example.premarital.therapistSchedule.model.TherapistSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapistScheduleRepository extends JpaRepository<TherapistSchedule, Long> {
}
