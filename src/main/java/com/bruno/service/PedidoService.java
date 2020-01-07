package com.bruno.service;

import java.util.Date;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.domain.ItemPedido;
import com.bruno.domain.PagamentoComBoleto;
import com.bruno.domain.Pedido;
import com.bruno.domain.Produto;
import com.bruno.domain.enums.EstadoPagamento;
import com.bruno.repository.ItemPedidoRepository;
import com.bruno.repository.PagamentoRepository;
import com.bruno.repository.PedidoRepository;
import com.bruno.repository.ProdutoRepository;
import com.bruno.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired

	private ProdutoService produtoService;

	public Pedido buscar(Long id) {
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("O pedido " + id + "não foi encontrado"));
		return pedido;
	}
	@Transactional
	public Pedido insert (Pedido pedido) {
		
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto =(PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto,pedido.getInstante());
		}
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		
		for(ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(produtoService.buscar(item.getProduto().getId()));
			item.setPreco(item.getProduto().getValor());
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		emailService.sendOrderConfigurationEmail(pedido);
		return pedido;
	}
}
