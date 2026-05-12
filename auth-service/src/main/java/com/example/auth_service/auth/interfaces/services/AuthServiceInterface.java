package com.example.auth_service.auth.interfaces.services;

import com.example.auth_service.auth.dto.request.LoginRequest;
import com.example.auth_service.auth.dto.request.RegisterRequest;
import com.example.auth_service.auth.dto.response.LoginResponse;
import com.example.auth_service.auth.dto.response.UserResponse;

public interface AuthServiceInterface  {
    LoginResponse login(LoginRequest request);
    UserResponse register(RegisterRequest request);

}
