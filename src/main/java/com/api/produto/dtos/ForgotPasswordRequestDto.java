package com.api.produto.dtos;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ForgotPasswordRequestDto {
    private String username;
    @Email
    private String email;
}
