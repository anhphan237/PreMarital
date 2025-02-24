package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.mappers.TherapistMapper;
import com.example.premarital.models.Therapist;
import com.example.premarital.models.User;
import com.example.premarital.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class TherapistMapperImpl implements TherapistMapper {
    private final UserRepository userRepository;

    public TherapistMapperImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public TherapistDTO toDTO(Therapist therapist) {
        if ( therapist == null ) {
            return null;
        }

        TherapistDTO therapistDTO = new TherapistDTO();

        therapistDTO.setUserId( therapistUserId( therapist ) );
        therapistDTO.setId( therapist.getId() );
        therapistDTO.setBio( therapist.getBio() );
        therapistDTO.setTherapistCertificationName( therapist.getTherapistCertificationName() );
        therapistDTO.setCertificationIssuedBy( therapist.getCertificationIssuedBy() );
        therapistDTO.setCertificationIssueDate( therapist.getCertificationIssueDate() );
        therapistDTO.setCertificationExpirationDate( therapist.getCertificationExpirationDate() );

        return therapistDTO;
    }

    @Override
    public Therapist toEntity(TherapistDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Therapist therapist = new Therapist();

        therapist.setUser( therapistDTOToUser( dto ) );
        therapist.setId( dto.getId() );
        therapist.setBio( dto.getBio() );
        therapist.setTherapistCertificationName( dto.getTherapistCertificationName() );
        therapist.setCertificationIssuedBy( dto.getCertificationIssuedBy() );
        therapist.setCertificationIssueDate( dto.getCertificationIssueDate() );
        therapist.setCertificationExpirationDate( dto.getCertificationExpirationDate() );

        return therapist;
    }

    private Long therapistUserId(Therapist therapist) {
        if ( therapist == null ) {
            return null;
        }
        User user = therapist.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User therapistDTOToUser(TherapistDTO therapistDTO) {
        if ( therapistDTO == null ) {
            return null;
        }

        User user = userRepository.findById( therapistDTO.getUserId() ).orElse( null );

        user.setId( therapistDTO.getUserId() );

        return user;
    }
}