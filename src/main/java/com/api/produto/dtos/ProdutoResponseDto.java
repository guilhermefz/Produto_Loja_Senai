package com.api.produto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProdutoResponseDto {
    private UUID id;
    private String nome;
    private String descricao;
    private Double preco;
    private UUID lojaId;
}
