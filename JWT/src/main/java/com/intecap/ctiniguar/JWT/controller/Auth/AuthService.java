package com.intecap.ctiniguar.JWT.controller.Auth;

import com.intecap.ctiniguar.JWT.Model.Role;
import com.intecap.ctiniguar.JWT.Model.User;
import com.intecap.ctiniguar.JWT.Model.UserRepository;
import com.intecap.ctiniguar.JWT.jsonwebtoken.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthReponse login(LoginRequest requestBody) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestBody.getUsername(), requestBody.getPassword()));
        UserDetails user = userRepository.findByUsername(requestBody.getUsername()).orElseThrow();

        String token =  jwtService.getToken(user);
        return AuthReponse.builder()
                .token(token)
                .build();
    }

    public AuthReponse register(RegisterRequest requestBody) {
        User user = User.builder()
                .username( requestBody.getUsername())
                .password(passwordEncoder.encode(requestBody.getPassword()) )
                .firstname(requestBody.getFirstname())
                .lastname(requestBody.getLastname())
                .country(requestBody.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return AuthReponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
