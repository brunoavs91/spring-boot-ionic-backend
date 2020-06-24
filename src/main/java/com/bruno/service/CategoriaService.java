package com.bruno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bruno.domain.Categoria;
import com.bruno.dto.CategoriaDTO;
import com.bruno.repository.CategoriaRepository;
import com.bruno.service.exception.DataIntegrityException;
import com.bruno.service.exception.ObjectNotFoundException;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Long id) {
		Categoria categoria= categoriaRepository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Categoria não foi encontrada"));
		return categoria;
	}
	
	public Categoria insert(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		
		Categoria categoriaBanco= find(categoria.getId());
		
		atualizarCategoria(categoriaBanco,categoria);
		return categoriaRepository.save(categoria);

	}

	private void atualizarCategoria(Categoria categoriaBanco, Categoria categoria) {
		categoriaBanco.setNome(categoria.getNome());
		
		
	}

	public void delete(Long id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);

		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não e possivel exclui categoria com produtos associados");
		}
	}
	public List<Categoria>findAll(){
		return categoriaRepository.findAll(); 
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		return categoriaRepository.findAll(PageRequest.of(page,linesPerPage,Direction.valueOf(direction), orderBy));
	}
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(),categoriaDTO.getNome());
	}
}
