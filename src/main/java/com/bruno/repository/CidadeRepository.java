package com.bruno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
