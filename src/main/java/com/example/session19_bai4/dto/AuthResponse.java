package com.example.session19_bai4.dto;

import lombok.*;

@Getter
@Setter

@Builder

@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String accessToken;

    private String refreshToken;
}