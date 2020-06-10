package com.bruno.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.domain.Cidade;
import com.bruno.domain.Estado;
import com.bruno.dto.CidadeDTO;
import com.bruno.dto.EstadoDTO;
import com.bruno.service.CidadeService;
import com.bruno.service.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> estados = estadoService.findAll();
		List<EstadoDTO> estadosDTO = estados.stream().map(e -> new EstadoDTO(e)).collect(Collectors.toList());
		return ResponseEntity.ok().body(estadosDTO);
	}
	
	@RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Long estadoId) {
		List<Cidade> cidades = cidadeService.findCidadeByEstados(estadoId);
		List<CidadeDTO> cidadesDTO = cidades.stream().map(c -> new CidadeDTO(c)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cidadesDTO);

	}
	
	
}
