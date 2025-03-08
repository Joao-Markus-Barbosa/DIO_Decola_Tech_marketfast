package com.marketfast.domain.produto.repository;


import com.marketfast.domain.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

