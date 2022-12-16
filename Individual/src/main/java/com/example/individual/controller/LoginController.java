package com.example.individual.controller;


import com.example.individual.business.LoginUseCase;
import com.example.individual.domain.LoginRequest;
import com.example.individual.domain.LoginResponse;
import com.example.individual.domain.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class LoginController {
    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginUseCase.login(loginRequest);
        System.out.println("Token" + loginResponse.getAccessToken());
        return ResponseEntity.ok(loginResponse);
    }
}
