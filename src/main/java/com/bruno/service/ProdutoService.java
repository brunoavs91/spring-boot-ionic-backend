package com.bruno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bruno.domain.Categoria;
import com.bruno.domain.Produto;
import com.bruno.repository.CategoriaRepository;
import com.bruno.repository.ProdutoRepository;
import com.bruno.service.exception.ObjectNotFoundException;

	

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto buscar(Long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("O produto " + id + "não foi encontrado"));
		return produto;
	}
	

	public Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

		List<Categoria> categorias = categoriaRepository.findAllById(ids);

		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
