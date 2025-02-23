package com.example.premarital.therapist.mapper;


import com.example.premarital.therapist.dto.TherapistDTO;
import com.example.premarital.therapist.model.Therapist;

public interface TherapistMapper {

    TherapistDTO toDTO(Therapist therapist);

    Therapist toEntity(TherapistDTO dto);
}
