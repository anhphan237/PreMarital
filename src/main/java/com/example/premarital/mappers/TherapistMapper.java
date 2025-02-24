package com.example.premarital.mappers;


import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.models.Therapist;

public interface TherapistMapper {

    TherapistDTO toDTO(Therapist therapist);

    Therapist toEntity(TherapistDTO dto);
}
