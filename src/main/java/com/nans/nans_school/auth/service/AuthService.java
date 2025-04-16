package com.nans.nans_school.auth.service;

import com.nans.nans_school.auth.request.LoginRequest;
import com.nans.nans_school.auth.request.RegisterRequest;
import com.nans.nans_school.user.User;
import com.nans.nans_school.utils.enums.Role;
import com.nans.nans_school.utils.exceptions.EmailAlreadyExistsException;
import com.nans.nans_school.utils.exceptions.InvalidCredentialsException;
import com.nans.nans_school.user.UserRepository;
import com.nans.nans_school.auth.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register (RegisterRequest request){
       User user = User.builder()
               .firstName(request.getFirstName())
               .lastName(request.getLastName())
               .email(request.getEmail())
               .password(passwordEncoder.encode(request.getPassword()))
               .role(Role.STUDENT)
               .build();
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
       userRepository.save(user);
       var jwtToken = jwtService.generateToken(user);
       return AuthenticationResponse.builder()
               .token(jwtToken)
                .build();
    }

    public  AuthenticationResponse login (LoginRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )

            );
        }catch (Exception e){
            throw new InvalidCredentialsException("Invalid email or password");
        }
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


}
