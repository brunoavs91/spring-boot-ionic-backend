package com.bruno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.domain.Categoria;
import com.bruno.repository.CategoriaRepository;
import com.bruno.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Long id) {
		Categoria categoria= categoriaRepository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Categoria não foi encontrada"));
		return categoria;
	}
	
	public Categoria insert(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
}
