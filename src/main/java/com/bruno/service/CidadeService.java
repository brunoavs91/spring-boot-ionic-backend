package com.bruno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.domain.Cidade;
import com.bruno.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> findCidadeByEstados(Long idEstado){
		return cidadeRepository.findCidadesByEstado(idEstado);
	}
}
