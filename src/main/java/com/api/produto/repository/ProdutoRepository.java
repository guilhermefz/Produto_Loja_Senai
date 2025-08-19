package com.api.produto.repository;

import com.api.produto.models.ProdutoModel;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.UUID;

public interface ProdutoRepository extends
        JpaRepository<ProdutoModel, UUID> {
    void deleteById(SingularAttribute<AbstractPersistable, Serializable> id);
}
