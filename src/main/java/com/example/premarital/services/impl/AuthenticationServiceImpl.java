package com.example.premarital.services.impl;

import com.example.premarital.mappers.UserMapper;
import com.example.premarital.models.AuthenticationResponse;
import com.example.premarital.models.RegisterRequest;
import com.example.premarital.models.Token;
import com.example.premarital.models.User;
import com.example.premarital.repositories.TokenRepository;
import com.example.premarital.repositories.UserRepository;
import com.example.premarital.services.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository; // thêm TokenRepository
    private final UserMapper userMapper;
    private final JwtServiceImpl jwtService;

    public AuthenticationServiceImpl(
            UserRepository userRepository,
            TokenRepository tokenRepository,
            JwtServiceImpl jwtService,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User newUser = new User();
        newUser.setUsername(request.getName());
        newUser.setPassword(request.getPassword());
        newUser.setEmail(request.getEmail());
        User createdUser = userRepository.save(newUser);
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

}
