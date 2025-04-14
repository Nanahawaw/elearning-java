package com.nans.nans_school.service;

import com.nans.nans_school.dto.InviteTutorRequest;
import com.nans.nans_school.dto.LoginRequest;
import com.nans.nans_school.dto.RegisterRequest;
import com.nans.nans_school.entities.User;
import com.nans.nans_school.enums.Role;
import com.nans.nans_school.exceptions.EmailAlreadyExistsException;
import com.nans.nans_school.exceptions.InvalidCredentialsException;
import com.nans.nans_school.repositories.UserRepository;
import com.nans.nans_school.utils.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.insert.ConflictClause;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<?> tutorInvitation (InviteTutorRequest request, String token) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User with this email already exists.");
        }
        return null;
    }

    
}
