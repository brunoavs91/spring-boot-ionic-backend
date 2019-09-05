package com.bruno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
