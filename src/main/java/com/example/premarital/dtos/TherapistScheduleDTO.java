package com.example.premarital.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class TherapistScheduleDTO {
    private Long id;
    private Long therapistId;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date availableDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isBooked;

    public TherapistScheduleDTO() {
    }

    public TherapistScheduleDTO(Long id, Long therapistId, Date availableDate, LocalDateTime startTime, LocalDateTime endTime, boolean isBooked) {
        this.id = id;
        this.therapistId = therapistId;
        this.availableDate = availableDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = isBooked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTherapistId() {
        return therapistId;
    }

    public void setTherapistId(Long therapistId) {
        this.therapistId = therapistId;
    }

    public Date getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
