package com.api.produto.controllers;

import com.api.produto.dtos.LojaDto;
import com.api.produto.dtos.ProdutoDto;
import com.api.produto.models.LojaModel;
import com.api.produto.models.ProdutoModel;
import com.api.produto.services.LojaService;
import com.api.produto.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController

@RequestMapping("/loja")
public class LojaController {
    private final LojaService lojaService;
    public LojaController(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    @GetMapping("teste")
    public String metodo1() {
        return "Loja Controller está ativo, isso é um teste da loja";
    }

    @RequestMapping("/listar")
    public List<LojaModel> listar() {
        return lojaService.listar();
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(
            @RequestBody @Valid LojaDto dto
    ) {

        LojaModel lojaModel = lojaService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(lojaModel);
    }

    @PostMapping("/apagar/{id}")
    public ResponseEntity<String> apagar(@PathVariable(value = "id") UUID id) {
        try{
            lojaService.delete(id);
            return ResponseEntity.ok("Loja apagado com sucesso!");
        }catch (Exception e) {
            //retorna error 500 com a mensagem de erro para o front
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: "+ e.getMessage());
        }

    }

    @GetMapping("/buscar")
    public List<LojaModel> buscar(@RequestParam String nomeBusca) {
        return lojaService.buscarPorNome(nomeBusca);
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editar(
            @RequestBody @Valid LojaDto dto,
            @PathVariable(value = "id") UUID id
    ) {

        try {
            LojaModel lojaEditado = lojaService.atualizar(dto, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(lojaEditado);
        } catch (Exception e) {
            //retorna error 500 com a mensagem de erro para o front
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: "+ e.getMessage());
        }

    }
}
