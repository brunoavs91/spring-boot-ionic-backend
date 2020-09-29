package com.bruno.dto;

import com.bruno.domain.Cidade;

public class CidadeDTO {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private EstadoDTO estadoDTO;

	public CidadeDTO() {

	}

	public CidadeDTO(Cidade cidade) {
		this.id = cidade.getId();
		this.nome = cidade.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EstadoDTO getEstadoDTO() {
		return estadoDTO;
	}

	public void setEstadoDTO(EstadoDTO estadoDTO) {
		this.estadoDTO = estadoDTO;
	}
	
	
}
