package org.example.loanmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.loanmanagement.dto.AuthResponseDto;
import org.example.loanmanagement.dto.RegisterDto;
import org.example.loanmanagement.entity.User;
import org.example.loanmanagement.enums.Role;
import org.example.loanmanagement.repository.UserRepository;
import org.example.loanmanagement.security.JwtUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public AuthResponseDto register(RegisterDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole())); // Stringdan Enumga o‘girish

        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponseDto(token);
    }


}
