package com.bruno.repository;

import java.util.Optional;

import com.bruno.domain.Cliente;

public interface ClienteRepositoryCustom {

	/**
	 * Buscando cliente por email 
	 * @param email
	 * @return
	 */
	Optional<Cliente> buscarClientePorEmail(String email);
}
