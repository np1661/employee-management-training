package com.training.employeemanagementtraining.controller;

import com.training.employeemanagementtraining.dto.LoginRequest;
import com.training.employeemanagementtraining.dto.LoginResponse;
import com.training.employeemanagementtraining.dto.RegisterRequest;
import com.training.employeemanagementtraining.service.jwt.AuthService;
import com.training.employeemanagementtraining.service.jwt.TokenBlacklistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenBlacklistService tokenBlacklistService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestHeader("Authorization") String authorizationHeader) {

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            return ResponseEntity.badRequest()
                    .body("Bearer token is required");
        }

        String token = authorizationHeader.substring(7);

        tokenBlacklistService.blacklistToken(token);

        return ResponseEntity.ok("Logout successful");
    }


}
