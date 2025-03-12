package org.example.loanmanagement.service;

import org.example.loanmanagement.dto.AuthResponseDto;
import org.example.loanmanagement.dto.LoginDto;
import org.example.loanmanagement.dto.RegisterDto;
import org.example.loanmanagement.entity.User;
import org.example.loanmanagement.enums.Role;
import org.example.loanmanagement.repository.UserRepository;
import org.example.loanmanagement.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponseDto register(RegisterDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole()));
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(LoginDto request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseDto(token);
    }

}
