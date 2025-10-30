package com.api.produto.services;

import com.api.produto.dtos.ForgotPasswordRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final EmailService emailService;

    public void forgotPassword
            (ForgotPasswordRequestDto forgotPasswordRequestDto) {
        String email = forgotPasswordRequestDto.getEmail();
        String newPassword = generateRadomPassword();
        emailService.sendNewPasswordEmail(email, newPassword);
    }

    private String generateRadomPassword() {
        //Define comprimento da senha
        int length = 10;
        //Define os caracteres possíveis
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        //Cria um gerador de números aleatórios
        Random random = new Random();
        //String builder uma string mutavel
        StringBuilder password = new StringBuilder(length);
        // Loop que vai rodar até completar a quantidade de caracteres desejada
        for (int i = 0; i < length; i++) {
            //Gera um indice aleatorio dentro do tamanho da string
            int randomCharIndex = random.nextInt(chars.length());
            //Pega o char correspondente e adiciona ao StringBuilder
            password.append(chars.charAt(randomCharIndex));
        }
        return password.toString();
    }
}
