package com.bruno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
