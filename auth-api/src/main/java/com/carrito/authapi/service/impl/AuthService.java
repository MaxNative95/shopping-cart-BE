package com.carrito.authapi.service.impl;
import com.carrito.authapi.dto.AuthRequest;
import com.carrito.authapi.dto.AuthResponse;
import com.carrito.authapi.dto.RegisterRequest;
import com.carrito.authapi.entity.User;
import com.carrito.authapi.repository.UserRepository;
import com.carrito.authapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .address(request.getAddress())
                .birthDate(request.getBirthDate())
                .build();
        userRepository.save(user);
        var jwt = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(jwt);
    }

    public AuthResponse authenticate(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }

        var jwt = jwtUtil.generateToken(request.getUsername());
        return new AuthResponse(jwt);
    }
}
