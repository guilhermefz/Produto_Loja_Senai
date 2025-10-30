package com.api.produto.controllers;

import com.api.produto.dtos.ForgotPasswordRequestDto;
import com.api.produto.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword
            (@RequestBody @Valid ForgotPasswordRequestDto forgotPasswordRequestDto) {
        try {
            authService.forgotPassword(forgotPasswordRequestDto);
            return ResponseEntity.ok("E-mail enviado com sucesso!");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Erro: "+ e.getMessage());
        }
    }
}
