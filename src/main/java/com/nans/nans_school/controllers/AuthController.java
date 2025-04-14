package com.nans.nans_school.controllers;

import com.nans.nans_school.dto.LoginRequest;
import com.nans.nans_school.dto.RegisterRequest;
import com.nans.nans_school.entities.User;
import com.nans.nans_school.service.AuthService;
import com.nans.nans_school.utils.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid
            @RequestBody
            RegisterRequest request
    ){

        return  ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid
            @RequestBody
            LoginRequest request
    ){
      return ResponseEntity.ok(authService.login(request));
    }

}
