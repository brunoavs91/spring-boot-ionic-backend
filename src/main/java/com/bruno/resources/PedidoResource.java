package com.bruno.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bruno.domain.Categoria;
import com.bruno.domain.Cliente;
import com.bruno.domain.Pedido;
import com.bruno.dto.CategoriaDTO;
import com.bruno.dto.ClienteNewDTO;
import com.bruno.service.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Long id) {
		Pedido pedido = pedidoService.buscar(id);
		
		return ResponseEntity.ok().body(pedido);
	}
	

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido){
		
		
		pedido=pedidoService.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedido.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Pedido> lista = pedidoService.findPage(page, linesPerPage, orderBy, direction);

		return ResponseEntity.ok().body(lista);
	}

}
