package com.example.dailydo.service.interfaces;

import com.example.dailydo.dto.UserDTO;
import com.example.dailydo.request.LoginRequest;
import com.example.dailydo.request.RegisterRequest;

import java.util.Map;

public interface AuthService {
    UserDTO register(RegisterRequest request);
    Map<String, Object> login(LoginRequest request);
    void logout(String email);
    String refreshToken(String refreshToken);
}