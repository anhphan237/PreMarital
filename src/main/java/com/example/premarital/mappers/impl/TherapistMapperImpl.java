package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.mappers.TherapistMajorMapper;
import com.example.premarital.mappers.TherapistMapper;
import com.example.premarital.models.Therapist;
import com.example.premarital.models.TherapistMajor;
import com.example.premarital.models.User;
import com.example.premarital.repositories.TherapistMajorRepository;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class TherapistMapperImpl implements TherapistMapper {
    private final UserRepository userRepository;
    private final TherapistMajorRepository therapistMajorRepository;

    public TherapistMapperImpl(UserRepository userRepository, TherapistMajorRepository therapistMajorRepository) {
        this.userRepository = userRepository;
        this.therapistMajorRepository = therapistMajorRepository;
    }

    @Override
    public TherapistDTO toDTO(Therapist therapist) {
        if ( therapist == null ) {
            return null;
        }

        TherapistDTO therapistDTO = new TherapistDTO();

        therapistDTO.setUserId( therapist.getUser().getId() );
        therapistDTO.setBio( therapist.getBio() );
        therapistDTO.setTherapistCertificationName( therapist.getTherapistCertificationName() );
        therapistDTO.setCertificationIssuedBy( therapist.getCertificationIssuedBy() );
        therapistDTO.setCertificationIssueDate( therapist.getCertificationIssueDate() );
        therapistDTO.setCertificationExpirationDate( therapist.getCertificationExpirationDate() );
        therapistDTO.setIsActive( therapist.getIsActive() );
        therapistDTO.setTreatmentCost( therapist.getTreatmentCost() );
        therapistDTO.setTherapistMajorId(
                therapist.getTherapistMajor() != null ? therapist.getTherapistMajor().getId() : null
        );

        return therapistDTO;
    }

    @Override
    public Therapist toEntityWithId(Long id, TherapistDTO updatedTherapistDTO) {
        if ( updatedTherapistDTO == null ) {
            return null;
        }

        Therapist existingTherapist = new Therapist();

        existingTherapist.setId(id);
        existingTherapist.setBio(updatedTherapistDTO.getBio());
        existingTherapist.setUser(userRepository.findById(id).orElse(null));
        existingTherapist.setTherapistMajor(therapistMajorRepository.findById(updatedTherapistDTO.getTherapistMajorId()).orElse(null));
        existingTherapist.setIsActive(updatedTherapistDTO.getIsActive());
        existingTherapist.setCertificationExpirationDate(updatedTherapistDTO.getCertificationExpirationDate());
        existingTherapist.setCertificationIssueDate(updatedTherapistDTO.getCertificationIssueDate());
        existingTherapist.setCertificationIssuedBy(updatedTherapistDTO.getCertificationIssuedBy());
        existingTherapist.setTherapistCertificationName(updatedTherapistDTO.getTherapistCertificationName());
        existingTherapist.setTreatmentCost(updatedTherapistDTO.getTreatmentCost());
        existingTherapist.setIsActive(updatedTherapistDTO.getIsActive());

        return existingTherapist;
    }

    @Override
    public Therapist toEntity(TherapistDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Therapist therapist = new Therapist();

        if ( dto.getUserId() != null ) {
            User user = userRepository.findById(dto.getUserId()).orElse(null);
            therapist.setUser( user );
        }
        therapist.setId( dto.getUserId() );
        therapist.setBio( dto.getBio() );
        therapist.setTherapistCertificationName( dto.getTherapistCertificationName() );
        therapist.setCertificationIssuedBy( dto.getCertificationIssuedBy() );
        therapist.setCertificationIssueDate( dto.getCertificationIssueDate() );
        therapist.setCertificationExpirationDate( dto.getCertificationExpirationDate() );
        therapist.setTreatmentCost( dto.getTreatmentCost() );
        therapist.setIsActive( dto.getIsActive() );
        if(dto.getTherapistMajorId() != null) {
            TherapistMajor therapistMajor = new TherapistMajor();
            therapistMajor.setId( dto.getTherapistMajorId() );
            therapist.setTherapistMajor( therapistMajor );
        }

        return therapist;
    }
}