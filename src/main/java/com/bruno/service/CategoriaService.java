package com.bruno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.domain.Categoria;
import com.bruno.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Long id) {
		Categoria categoria= categoriaRepository.findById(id).orElse(null);
		return categoria;
	}
}
