package com.api.produto.controllers;

import com.api.produto.dtos.ProdutoDto;
import com.api.produto.models.LojaModel;
import com.api.produto.models.ProdutoModel;
import com.api.produto.repository.LojaRepository;
import com.api.produto.services.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@CrossOrigin(origins = "*")
@RestController

@RequestMapping("/produtos")
public class ProdutoController {
        private final ProdutoService produtoService;
    private final LojaRepository lojaRepository;

    public ProdutoController(ProdutoService produtoService, LojaRepository lojaRepository) {
        this.produtoService = produtoService;
        this.lojaRepository = lojaRepository;
    }

        @GetMapping("meu-get")
        public String metodo1() {
            return "Produto Controller está ativo, isso é um teste teste1";
        }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(
            @RequestBody @Valid ProdutoDto dto
    ) {

        Optional<LojaModel> loja = lojaRepository.findById(dto.getLojaId());
        if (!loja.isPresent()) {
            return ResponseEntity.badRequest().body("Loja não existe:" + dto.getLojaId());
        }
        //Cria o modelo do produto e associa DTO com MODEL
        ProdutoModel produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(dto, produtoModel, "id", "lojaModel");
        produtoModel.setLojaModel(loja.get()); //associar

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(produtoModel));

    }

    @RequestMapping("/listar")
    public List<ProdutoModel> listar() {
        return ProdutoService.listar();
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editar(
        @RequestBody @Valid ProdutoDto dto,
        @PathVariable(value = "id") UUID id
            ) {

        try {
            ProdutoModel produtoEditado = produtoService.atualizar(dto, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoEditado);
        } catch (Exception e) {
            //retorna error 500 com a mensagem de erro para o front
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: "+ e.getMessage());
        }

    }

    @PostMapping("/apagar/{id}")
    public ResponseEntity<String> apagar(@PathVariable(value = "id") UUID id) {
        try{
            produtoService.delete(id);
            return ResponseEntity.ok("Produto apagado com sucesso!");
        }catch (Exception e) {
            //retorna error 500 com a mensagem de erro para o front
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: "+ e.getMessage());
        }

    }

    @GetMapping("/buscar")
    public List<ProdutoModel> buscar(@RequestParam String nomeBusca){
        return produtoService.buscarPorNome(nomeBusca);
    }



}
