package com.example.premarital.services.impl;

import com.example.premarital.dtos.UserDTO;
import com.example.premarital.mappers.UserMapper;
import com.example.premarital.mappers.impl.UserMapperImpl;
import com.example.premarital.models.*;
import com.example.premarital.repositories.RoleRepository;
import com.example.premarital.repositories.TherapistRepository;
import com.example.premarital.repositories.TokenRepository;
import com.example.premarital.repositories.UserRepository;
import com.example.premarital.services.AuthenticationService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository; // thêm TokenRepository
    private final UserMapperImpl userMapper;
    private final JwtServiceImpl jwtService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TherapistRepository therapistRepository;

    public AuthenticationServiceImpl(
            UserRepository userRepository,
            TokenRepository tokenRepository,
            JwtServiceImpl jwtService,
            UserMapperImpl userMapper,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, TherapistRepository therapistRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.therapistRepository = therapistRepository;
    }

    @Override
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        User newUser = new User();
        newUser.setUsername(request.getName());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());
        newUser.setRole(roleRepository.getReferenceById(request.getRoleId()));
        newUser.setIsActive(true);
        User createdUser = userRepository.save(newUser);
        if(request.getRoleId() == 2){
            Therapist therapist = new Therapist();
            therapist.setUser(createdUser);
            therapist.setIsActive(true);
            System.out.println("Therapist User ID: " + therapist.getUser().getId()); // Debug
            therapistRepository.save(therapist);
        }
        String jwtToken = jwtService.generateToken(createdUser);
        // lưu token vào database
        Token token = Token.builder()
                .userId(createdUser.getId())
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
        return AuthenticationResponse.builder()
                .userDto(userMapper.toDTO(createdUser))
                .token(jwtToken)
                .build();
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
