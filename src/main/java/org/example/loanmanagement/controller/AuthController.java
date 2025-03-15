package org.example.loanmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.loanmanagement.dto.AuthResponseDto;
import org.example.loanmanagement.dto.LoginDto;
import org.example.loanmanagement.dto.RegisterDto;
import org.example.loanmanagement.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
