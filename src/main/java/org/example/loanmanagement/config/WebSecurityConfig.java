package org.example.loanmanagement.config;

import org.example.loanmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private UserService userService;

}
