package com.bruno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.domain.Endereco;

public interface EnderecoRepository  extends JpaRepository<Endereco, Long>{

}
