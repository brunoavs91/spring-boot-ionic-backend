package com.bruno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
