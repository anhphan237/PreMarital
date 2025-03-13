package com.example.premarital.services.impl;

import com.example.premarital.dtos.UserDTO;
import com.example.premarital.exceptions.DuplicateUserException;
import com.example.premarital.exceptions.InvalidDataException;
import com.example.premarital.exceptions.UserNotFoundException;
import com.example.premarital.mappers.impl.UserMapperImpl;
import com.example.premarital.models.*;
import com.example.premarital.repositories.RoleRepository;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.repositories.TokenRepository;
import com.example.premarital.repositories.UserRepository;
import com.example.premarital.services.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository; // thêm TokenRepository
    private final UserMapperImpl userMapper;
    private final JwtServiceImpl jwtService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TherapistRepository therapistRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);


    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException("Email " + request.getEmail() + " is already registered.");
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new InvalidDataException("Invalid role ID: " + request.getRoleId()));

        User createdUser = createUser(request, role);

        if (request.getRoleId() == 2) {
            createTherapist(createdUser);
        }

        String jwtToken = jwtService.generateToken(createdUser);
        saveToken(createdUser, jwtToken);

        return AuthenticationResponse.builder()
                .userDto(userMapper.toDTO(createdUser))
                .token(jwtToken)
                .build();
    }

    private User createUser(RegisterRequest request, Role role) {
        User newUser = new User();
        newUser.setUsername(request.getName());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());
        newUser.setRole(role);
        newUser.setIsActive(true);

        User savedUser = userRepository.save(newUser);
        logger.info("User with ID {} registered successfully.", savedUser.getId());
        return savedUser;
    }

    private void createTherapist(User user) {
        Therapist therapist = new Therapist();
        therapist.setUser(user);
        therapist.setIsActive(true);
        therapistRepository.save(therapist);
        logger.info("Therapist profile created for User ID: {}", user.getId());
    }

    private void saveToken(User user, String jwtToken) {
        Token token = Token.builder()
                .userId(user.getId())
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
        logger.info("JWT token saved for User ID: {}", user.getId());
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        Token token = Token.builder()
                .userId(user.getId())
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
        UserDTO userDto = userMapper.toDTO(user);
        return AuthenticationResponse.builder()
                .userDto(userDto)
                .token(jwtToken)
                .build();
    }
}
