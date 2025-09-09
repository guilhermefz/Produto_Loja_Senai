package com.api.produto.repository;

import com.api.produto.models.LojaModel;
import com.api.produto.models.ProdutoModel;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface LojaRepository extends
        JpaRepository<LojaModel, UUID> {
    void deleteById(SingularAttribute<AbstractPersistable, Serializable> id);

    //List<ProdutoModel> findByNome(String nome);
    List<LojaModel> findByNomeContainingIgnoreCase(String nome);
    //List<LojaModel> findByNomeContainingIgnoreCase(String nome, String cnpj, String endereco, String telefone);
}
