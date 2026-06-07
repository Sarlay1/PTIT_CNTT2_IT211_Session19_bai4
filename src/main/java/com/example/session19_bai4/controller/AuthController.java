package com.example.session19_bai4.controller;

import com.example.session19_bai4.dto.AuthResponse;
import com.example.session19_bai4.dto.LoginRequest;
import com.example.session19_bai4.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request
    ){
        return authenticationService.login(request);
    }

    @GetMapping("/test")
    public String test(){
        return "API OK";
    }
}