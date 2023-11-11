package com.intecap.ctiniguar.JWT.controller.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(value = "/login")
    public ResponseEntity<AuthReponse> Login(@RequestBody LoginRequest requestBody){

        return ResponseEntity.ok(authService.login(requestBody));
    }
    @PostMapping(value = "/register")
    public ResponseEntity<AuthReponse> register(@RequestBody RegisterRequest requestBody){
        return  ResponseEntity.ok(authService.register(requestBody));
    }
}
