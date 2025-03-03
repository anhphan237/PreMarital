package com.example.premarital.mappers.impl;

import com.example.premarital.dtos.TherapistDTO;
import com.example.premarital.mappers.TherapistMajorMapper;
import com.example.premarital.mappers.TherapistMapper;
import com.example.premarital.models.Therapist;
import com.example.premarital.models.User;
import com.example.premarital.repositories.TherapistMajorRepository;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class TherapistMapperImpl implements TherapistMapper {
    private final UserRepository userRepository;
    private final TherapistMajorMapper therapistMajorMapper;
    private final TherapistMajorRepository therapistMajorRepository;

    public TherapistMapperImpl(UserRepository userRepository, TherapistMajorMapper therapistMajorMapper, TherapistMajorRepository therapistMajorRepository) {
        this.userRepository = userRepository;
        this.therapistMajorMapper = therapistMajorMapper;
        this.therapistMajorRepository = therapistMajorRepository;
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
        therapistDTO.setTherapistMajorId(
                therapist.getTherapistMajor() != null ? therapist.getTherapistMajor().getId() : null
        );

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
        therapist.setTherapistMajor( therapistMajorRepository.getById( dto.getTherapistMajorId() ) );

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