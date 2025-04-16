package com.nans.nans_school.auth;

import com.nans.nans_school.auth.service.AuthService;
import com.nans.nans_school.auth.request.LoginRequest;
import com.nans.nans_school.auth.request.RegisterRequest;
import com.nans.nans_school.auth.response.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
