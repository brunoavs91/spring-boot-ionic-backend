package com.bruno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.domain.Endereco;

@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Long>{

}
