package com.example.auth_service.auth.controller;

import com.example.auth_service.auth.dto.response.ApiResponse;
import com.example.auth_service.auth.dto.response.LoginResponse;
import com.example.auth_service.auth.dto.response.UserResponse;
import com.example.auth_service.auth.dto.request.LoginRequest;
import com.example.auth_service.auth.dto.request.RegisterRequest;
import com.example.auth_service.auth.interfaces.services.AuthServiceInterface;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {


    private final AuthServiceInterface authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login( @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(
                ApiResponse.success("Login berhasil", response)
        );
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register( @Valid @RequestBody RegisterRequest request) {

        UserResponse response = authService.register(request);

        return ResponseEntity.ok(
                ApiResponse.success("Register berhasil", response)
        );
    }


}
