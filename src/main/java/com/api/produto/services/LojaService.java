package com.api.produto.services;


import com.api.produto.dtos.LojaDto;
import com.api.produto.dtos.ProdutoDto;
import com.api.produto.models.LojaModel;
import com.api.produto.models.ProdutoModel;
import com.api.produto.repository.LojaRepository;
import com.api.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LojaService {
    private static LojaRepository lojaRepository;

    public LojaService(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    public static List<LojaModel> listar() {
        return lojaRepository.findAll();
    }

    public LojaModel create(LojaDto dto) {
        LojaModel loja = new LojaModel();
        loja.setNome(dto.getNome());
        loja.setDescricao(dto.getDescricao());
        loja.setPreco(dto.getPreco());
        return lojaRepository.save(loja);
    }

    public void delete(UUID id) {
        LojaModel existente = lojaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("produto não encontrado"));
        lojaRepository.delete(existente);
    }

    public List<LojaModel> buscarPorNome(String nomeBusca) {
        return lojaRepository.findByNomeContainingIgnoreCase(nomeBusca);
    }

    public LojaModel atualizar(LojaDto dto, UUID id) {
        LojaModel existente = lojaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("loja não encontrado"));
        existente.setNome(dto.getNome());
        existente.setDescricao(dto.getDescricao());
        existente.setPreco(dto.getPreco());
        return lojaRepository.save(existente);
    }
}
