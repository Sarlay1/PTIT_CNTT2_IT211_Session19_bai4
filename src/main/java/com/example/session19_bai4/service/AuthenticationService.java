package com.example.session19_bai4.service;

import com.example.session19_bai4.dto.*;
import com.example.session19_bai4.entity.*;
import com.example.session19_bai4.repository.EmployeeRepository;
import com.example.session19_bai4.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final EmployeeRepository employeeRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request){

        Employee employee =
                employeeRepository
                        .findByUsername(
                                request.getUsername()
                        )
                        .orElseThrow();

        String accessToken =
                jwtService.generateAccessToken(employee);

        String refreshToken =
                jwtService.generateRefreshToken(employee);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}