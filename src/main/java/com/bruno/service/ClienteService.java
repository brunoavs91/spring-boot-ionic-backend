package com.bruno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bruno.domain.Cidade;
import com.bruno.domain.Cliente;
import com.bruno.domain.Endereco;
import com.bruno.dto.ClienteDTO;
import com.bruno.dto.ClienteNewDTO;
import com.bruno.repository.CidadeRepository;
import com.bruno.repository.ClienteRepository;
import com.bruno.repository.EnderecoRepository;
import com.bruno.service.exception.DataIntegrityException;
import com.bruno.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Long id) {
		Cliente cliente= clienteRepository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Categoria não foi encontrada"));
		return cliente;
	}
	
	
	public Cliente insert(Cliente cliente) {
		cliente=clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
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
	
	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente clie = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), null, null);

		Cidade cid = cidadeRepository.findById(clienteNewDTO.getCidadeId()).orElseThrow(null);
		Endereco end = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(),
				clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), clie, cid);

		clie.getEnderecos().add(end);
		clie.getTelefones().add(clienteNewDTO.getTelofone1());
		if (clienteNewDTO.getTelefone2() != null) {
			clie.getTelefones().add(clienteNewDTO.getTelefone2());
		}
		if (clienteNewDTO.getTelefone3() != null) {
			clie.getTelefones().add(clienteNewDTO.getTelefone3());
		}

		return clie;

	}
	
	private void atualizarCliente(Cliente cliente,Cliente clienteDadosNovos) {
		cliente.setNome(clienteDadosNovos.getNome());
		cliente.setEmail(clienteDadosNovos.getEmail());
	}
	
}
