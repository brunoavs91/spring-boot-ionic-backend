package com.bruno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.domain.Pedido;
import com.bruno.repository.PedidoRepository;
import com.bruno.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido buscar(Long id) {
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("O pedido " + id + "não foi encontrado"));
		return pedido;
	}
}
