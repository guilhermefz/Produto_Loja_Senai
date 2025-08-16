package com.api.produto.controllers;

import com.api.produto.dtos.ProdutoDto;
import com.api.produto.models.ProdutoModel;
import com.api.produto.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

@RequestMapping("/produtos")
public class ProdutoController {
  private final ProdutoService produtoService;
  public ProdutoController(ProdutoService produtoService) {
      this.produtoService = produtoService;
  }

    @GetMapping("meu-get")
    public String metodo1(){
        return "Produto Controller está ativo";
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(
            @RequestBody @Valid ProdutoDto dto
    ){
      //retorna apenas as mensagens de erro
//      if(bindingResult.hasErrors()){
//
//          var errors = bindingResult.getFieldErrors().stream().
//                  map(fe -> Map.of("messagem", fe.getDefaultMessage())).toList();
//          return ResponseEntity.badRequest().body(Map.of("erros", errors));

          //retorno padrão
          //return new ResponseEntity<>(bindingResult.getAllErrors(),
          //       HttpStatus.BAD_REQUEST);
      //}
        ProdutoModel produto = new ProdutoModel();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        ProdutoModel salvo = produtoService.create(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

}
