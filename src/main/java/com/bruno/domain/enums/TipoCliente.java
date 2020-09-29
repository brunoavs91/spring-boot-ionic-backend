package com.bruno.domain.enums;

import java.util.Objects;

public enum TipoCliente {

	PESSOAFISICA(1,"Pessoa Fisica"),
	PESSOAJURIDICA(2,"Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod,String descricao) {
		this.cod=cod;
		this.descricao=descricao;
	}
	
	public int getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		if(cod== null) {
			return null;
		}
		for(TipoCliente t : TipoCliente.values()) {
			if(Objects.equals(cod, t.getCod())) {
				return t;
			}
		}
		throw new IllegalArgumentException("Id invalido"+cod);
	}
	
	public static TipoCliente toEnum(String cod) {
		if(cod== null) {
			return null;
		}
		for(TipoCliente t : TipoCliente.values()) {
			if(Objects.equals(Integer.parseInt(cod), t.getCod())) {
				return t;
			}
		}
		throw new IllegalArgumentException("Id invalido"+cod);
	}
}
