package com.carrito.authapi.controller;
import com.carrito.authapi.dto.AuthRequest;
import com.carrito.authapi.dto.AuthResponse;
import com.carrito.authapi.dto.RegisterRequest;
import com.carrito.authapi.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/version")
    public ResponseEntity<String> version() {
        System.out.println(">>> Entró al endpoint /version");
        return ResponseEntity.ok("Auth API v1.0.0");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        System.out.println(">>> Entró al endpoint login");
        System.out.println(request);
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
