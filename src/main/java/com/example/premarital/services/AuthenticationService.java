package com.example.premarital.services;

import com.example.premarital.models.AuthenticationResponse;
import com.example.premarital.models.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
}
