package com.bruno.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bruno.domain.Cidade;
import com.bruno.domain.Cliente;
import com.bruno.domain.Endereco;
import com.bruno.domain.enums.Perfil;
import com.bruno.dto.ClienteDTO;
import com.bruno.dto.ClienteNewDTO;
import com.bruno.repository.CidadeRepository;
import com.bruno.repository.ClienteRepository;
import com.bruno.repository.EnderecoRepository;
import com.bruno.security.UserSS;
import com.bruno.service.exception.AuthorizationException;
import com.bruno.service.exception.DataIntegrityException;
import com.bruno.service.exception.FileException;
import com.bruno.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefixo;

	@Value("${img.profile.size}")
	private Integer size;

	public Cliente find(Long id) {

		UserSS user = UserService.authenticated();

		if (user == null || !user.hasHole(Perfil.ADMIN) && id.equals(user.getId())) {

			throw new AuthorizationException("Acesso negado");
		}
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não foi encontrada"));
		return cliente;
	}

	public Cliente insert(Cliente cliente) {
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	public Cliente update(Cliente cliente) {

		Cliente clienteBanco = find(cliente.getId());
		atualizarCliente(clienteBanco, cliente);

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

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public ClienteDTO findByEmail(String email) {
		UserSS user = UserService.authenticated();

		if (user == null || !user.hasHole(Perfil.ADMIN) && email.equals(user.getUsername())) {

			throw new AuthorizationException("Acesso negado");
		}
//		Cliente cliente = clienteRepository.buscarClientePorEmail(email)
//				.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado:" + user.getId()));
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Objeto nao encontrado:" + user.getId());
		}

		return fromCliente(cliente);

	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		return clienteRepository.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
	}

	public ClienteDTO fromCliente(Cliente cliente) {

		return new ClienteDTO(cliente);
	}

	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente clie = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), null, null,
				passwordEncoder.encode(clienteNewDTO.getSenha()));

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

	private void atualizarCliente(Cliente cliente, Cliente clienteDadosNovos) {
		cliente.setNome(clienteDadosNovos.getNome());
		cliente.setEmail(clienteDadosNovos.getEmail());
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		// criando um nome fixo para os arquivos de imagem
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);

		String fileName = prefixo + user.getId() + ".jpg";

		Pair<InputStream, byte[]> pair = imageService.getInputStream(jpgImage, "jpg");

		return s3Service.uploadFile(pair.getFirst(), fileName, "image", pair.getSecond());

//		URI uri = s3Service.uploadFile(multipartFile);
//		Cliente cliente = clienteRepository.findById(user.getId())
//				.orElseThrow(() -> new ObjectNotFoundException("Cliente nao encontrado"));
//
//		//Se quiser guardar a URL na tabela cliente so voltar o codigo anterior
////		cliente.setImageURL(uri.toString());
//		clienteRepository.save(cliente);
//
//		return uri;
	}

}
