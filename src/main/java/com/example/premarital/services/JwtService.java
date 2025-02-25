package com.example.premarital.services;

import com.example.premarital.models.User;

public interface JwtService {
    String generateToken(User user);
}

