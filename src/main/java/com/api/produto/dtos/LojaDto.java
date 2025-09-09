package com.api.produto.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LojaDto {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotNull(message = "O cnpj é obrigatório")
    private String cnpj;
    private String endereco;
    @NotNull(message = "O telefone é obrigatório")
    private String telefone;

}
