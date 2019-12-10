package com.bruno.dto;

import java.io.Serializable;

import com.bruno.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private Double valor;
	
	public ProdutoDTO() {
		
	}
	
	public ProdutoDTO(Produto produto) {
		id= produto.getId();
		nome = produto.getNome();
		valor = produto.getValor();
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	
}
