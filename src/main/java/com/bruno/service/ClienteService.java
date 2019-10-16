package com.bruno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bruno.domain.Cliente;
import com.bruno.dto.ClienteDTO;
import com.bruno.repository.ClienteRepository;
import com.bruno.service.exception.DataIntegrityException;
import com.bruno.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Long id) {
		Cliente cliente= clienteRepository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Categoria não foi encontrada"));
		return cliente;
	}
	
	
	public Cliente insert(Cliente categoria) {
		return clienteRepository.save(categoria);
	}
	
	public Cliente update(Cliente cliente) {

		Cliente clienteBanco =find(cliente.getId());
		atualizarCliente(clienteBanco,cliente);

		return clienteRepository.save(clienteBanco);

	}

	public void delete(Long id) {
		find(id);
		try {
			clienteRepository.deleteById(id);

		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não e possivel exclui porque a propiedades  associadas");
		}
	}
	public List<Cliente>findAll(){
		return clienteRepository.findAll(); 
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		return clienteRepository.findAll(PageRequest.of(page,linesPerPage,Direction.valueOf(direction), orderBy));
	}
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(),clienteDTO.getNome() , clienteDTO.getEmail(),null, null);
	}
	
	private void atualizarCliente(Cliente cliente,Cliente clienteDadosNovos) {
		cliente.setNome(clienteDadosNovos.getNome());
		cliente.setEmail(clienteDadosNovos.getEmail());
	}
	
}
