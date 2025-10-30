package com.api.produto.repository;

import com.api.produto.dtos.ProdutoResponseDto;
import com.api.produto.models.ProdutoModel;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends
        JpaRepository<ProdutoModel, UUID> {
    void deleteById(SingularAttribute<AbstractPersistable, Serializable> id);

    //List<ProdutoModel> findByNome(String nome);

    List<ProdutoModel> findByNomeContainingIgnoreCase(String nome);

    @Query("""
       select new com.api.produto.dtos.ProdutoResponseDto(
           p.id, p.nome, p.descricao, p.preco, p.lojaModel.id
       )
       from ProdutoModel p
       """)
    List<ProdutoResponseDto> listarProjetado();

}
