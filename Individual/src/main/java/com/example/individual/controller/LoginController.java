package com.example.individual.controller;


import com.example.individual.business.LoginUseCase;
import com.example.individual.business.exceptions.EmailNotFoundException;
import com.example.individual.business.exceptions.PasswordIncorrectException;
import com.example.individual.domain.LoginRequest;
import com.example.individual.domain.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class LoginController {
    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = loginUseCase.login(loginRequest);
            System.out.println("Token" + loginResponse.getAccessToken());
            return ResponseEntity.ok(loginResponse);
        } catch (PasswordIncorrectException | EmailNotFoundException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
